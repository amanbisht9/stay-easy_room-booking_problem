package roombook.manage.stay_easy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import roombook.manage.stay_easy.model.Booking;
import roombook.manage.stay_easy.service.BookingService;

@RestController
@RequestMapping("/book")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/{hotelId}")
    public ResponseEntity<Booking> registerHotel(@PathVariable("hotelId") int hotelId, @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7);
        Booking booking = bookingService.bookHotelRoomS(hotelId, token);
        
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel/{bookId}")
    public ResponseEntity<Boolean> removeHotel(@PathVariable("bookId") int bookId) {
        
        boolean resp = bookingService.cancelBookingS(bookId);
        
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    
}
