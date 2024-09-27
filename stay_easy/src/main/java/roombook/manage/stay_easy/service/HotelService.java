package roombook.manage.stay_easy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roombook.manage.stay_easy.exception.RegistrationException;
import roombook.manage.stay_easy.model.Hotel;
import roombook.manage.stay_easy.repository.IHotelRepository;
import roombook.manage.stay_easy.utils.ValidationChecks;

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
    
}
