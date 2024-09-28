package roombook.manage.stay_easy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import roombook.manage.stay_easy.dto.HotelCreateRequest;
import roombook.manage.stay_easy.model.Hotel;
import roombook.manage.stay_easy.service.HotelService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/register")
    public ResponseEntity<Hotel> registerHotel(@RequestBody HotelCreateRequest hotelCreateRequest) {
        
        String hotelTitle= hotelCreateRequest.getHotelName();
        String location = hotelCreateRequest.getLocation();
        String description = hotelCreateRequest.getDescription();
        int roomAvailable = hotelCreateRequest.getRoomAvailable();

        Hotel hotel = hotelService.registerHotelS(hotelTitle, location, description, roomAvailable);
        
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> removeHotel(@PathVariable("id") int id) {
        
        boolean resp = hotelService.removeHotelS(id);
        
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/availableHotels")
    public ResponseEntity<List<Hotel>> getAllHotel() {

        List<Hotel> hotels = hotelService.getAllAvailableHotelS();
        
        return new ResponseEntity<>(hotels, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("id") int id, @RequestBody HotelCreateRequest hotelRequest) {
        //TODO: process PUT request

        String hotelTitle= hotelRequest.getHotelName();
        String location = hotelRequest.getLocation();
        String description = hotelRequest.getDescription();
        int roomAvailable = hotelRequest.getRoomAvailable();

        Hotel updatedHotel = hotelService.updateHotelS(id, hotelTitle, location, description, roomAvailable);
        
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
    }

    
}
