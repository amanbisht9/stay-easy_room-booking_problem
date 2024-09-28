package roombook.manage.stay_easy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roombook.manage.stay_easy.exception.FetchException;
import roombook.manage.stay_easy.exception.FieldException;
import roombook.manage.stay_easy.exception.RegistrationException;
import roombook.manage.stay_easy.model.Hotel;
import roombook.manage.stay_easy.repository.IHotelRepository;
import roombook.manage.stay_easy.utils.ValidationChecks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private IHotelRepository hotelRepository;

    public Hotel registerHotelS(String hotelTitle, String location, String description, int roomAvailable) {
        try {
           
            if(!ValidationChecks.namePatternCheck(hotelTitle)){
                throw new RegistrationException("Hotel title should only have alphabets and spaces and cannot be empty or null");
            }

            if(!ValidationChecks.namePatternCheck(location)){
                throw new RegistrationException("Hotel location should only have alphabets and spaces and cannot be empty or null");
            }

            if(!ValidationChecks.namePatternCheck(description)){
                throw new RegistrationException("Hotel description should only have alphabets and spaces and cannot be empty or null");
            }

            if(roomAvailable<=0){
                throw new RegistrationException("Number of rooms should be greater than 0");
            }
            
            Hotel hotel = new Hotel();
            hotel.setHotelName(hotelTitle);
            hotel.setLocation(location);
            hotel.setDescription(description);
            hotel.setRoomAvailable(roomAvailable);


            hotelRepository.save(hotel);

            return hotel;


        } catch (Exception e) {
            throw new RegistrationException(e.getMessage());
        }
    }

    public boolean removeHotelS(int id) {
        Optional<Hotel> fHotel = hotelRepository.findById(id);
        try {
            if(!fHotel.isPresent()){
                throw new FieldException("Hotel id does not exist");
            }
    
            hotelRepository.deleteById(id);
            return true;
            
            
        } catch (Exception e) {
            throw new FieldException(e.getMessage());
        }
    }

    public List<Hotel> getAllAvailableHotelS() {
        List<Hotel> availableHotels = new ArrayList<>();
        List<Hotel> fHotel = hotelRepository.findAll();
        try {
            if(fHotel.isEmpty() || fHotel.size() == 0 || fHotel == null){
                throw new FetchException("No hotel is available");
            }

            for(Hotel val : fHotel){
                if(val.getRoomAvailable()>0){
                    availableHotels.add(val);
                }
            }

            return availableHotels;
            
        } catch (Exception e) {
            throw new FetchException(e.getMessage());
        }
    }

    public Hotel updateHotelS(int id, String hotelTitle, String location, String description, int roomAvailable) {
        try {

            Optional<Hotel> hotel = hotelRepository.findById(id);
            
            if(!hotel.isPresent()){
                throw new FetchException("Hotel id not found");
            }
           
            if(!ValidationChecks.namePatternCheck(hotelTitle)){
                throw new FieldException("Hotel title should only have alphabets and spaces and cannot be empty or null");
            }

            if(!ValidationChecks.namePatternCheck(location)){
                throw new FieldException("Hotel location should only have alphabets and spaces and cannot be empty or null");
            }

            if(!ValidationChecks.namePatternCheck(description)){
                throw new FieldException("Hotel description should only have alphabets and spaces and cannot be empty or null");
            }

            if(roomAvailable<0){
                throw new FieldException("Number of rooms should be greater than or equal to 0");
            }
            
            hotel.get().setHotelName(hotelTitle);
            hotel.get().setLocation(location);
            hotel.get().setDescription(description);
            hotel.get().setRoomAvailable(roomAvailable);


            hotelRepository.save(hotel.get());

            return hotel.get();


        } catch (Exception e) {
            throw new RegistrationException(e.getMessage());
        }
    }
    
}
