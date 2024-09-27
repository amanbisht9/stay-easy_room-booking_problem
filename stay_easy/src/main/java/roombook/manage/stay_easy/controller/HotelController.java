package roombook.manage.stay_easy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import roombook.manage.stay_easy.dto.HotelCreateRequest;
import roombook.manage.stay_easy.model.Hotel;
import roombook.manage.stay_easy.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/register")
    public ResponseEntity<Hotel> registerBook(@RequestBody HotelCreateRequest hotelCreateRequest) {
        
        String hotelTitle= hotelCreateRequest.getHotelName();
        String location = hotelCreateRequest.getLocation();
        String description = hotelCreateRequest.getDescription();
        int roomAvailable = hotelCreateRequest.getRoomAvailable();

        Hotel hotel = hotelService.registerHotelS(hotelTitle, location, description, roomAvailable);
        
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    
}
