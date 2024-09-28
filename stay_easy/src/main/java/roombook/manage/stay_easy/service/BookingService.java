package roombook.manage.stay_easy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roombook.manage.stay_easy.exception.FetchException;
import roombook.manage.stay_easy.exception.FieldException;
import roombook.manage.stay_easy.model.Booking;
import roombook.manage.stay_easy.model.Hotel;
import roombook.manage.stay_easy.model.User;
import roombook.manage.stay_easy.repository.IBookingRepository;
import roombook.manage.stay_easy.repository.IHotelRepository;
import roombook.manage.stay_easy.repository.IUserRepository;
import roombook.manage.stay_easy.security.JwtHelper;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class BookingService {
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    private IHotelRepository hotelRepository;

    public Booking bookHotelRoomS(int hotelId, String token) {


        try {
            //get email from token of user
            String email = jwtHelper.getUsernameFromToken(token);
            
            Optional<User> fUser = userRepository.findByEmail(email);

            if(!fUser.isPresent()){
                throw new FetchException("User not found");
            }

            Optional<Hotel> fHotel = hotelRepository.findById(hotelId);

            if(!fHotel.isPresent()){
                throw new FetchException("Hotel not found");
            }

            if(fHotel.get().getRoomAvailable()==0){
                throw new FetchException("Hotel rooms are full");
            }

            Booking booking = new Booking();
            booking.setUser(fUser.get());
            booking.setHotel(fHotel.get());
            booking.setBookingDate(LocalDateTime.now());

            bookingRepository.save(booking);

            fHotel.get().setRoomAvailable((fHotel.get().getRoomAvailable())-1);

            hotelRepository.save(fHotel.get());

            return booking;

            
        } catch (Exception e) {
            throw new FetchException(e.getMessage());
        }
    }

    public boolean cancelBookingS(int bookId) {
        Optional<Booking> fBooking = bookingRepository.findById(bookId);
        try {
            if(!fBooking.isPresent()){
                throw new FieldException("Booking id does not exist");
            }
            //GETTING HOTEL ID TO UPDATE ROOM aVALIBILITY
            Hotel hotel = fBooking.get().getHotel();
    
            bookingRepository.deleteById(bookId);

            hotel.setRoomAvailable((hotel.getRoomAvailable())+1);

            hotelRepository.save(hotel);
            
            return true;
            
            
        } catch (Exception e) {
            throw new FieldException(e.getMessage());
        }
    }

    

    
}
