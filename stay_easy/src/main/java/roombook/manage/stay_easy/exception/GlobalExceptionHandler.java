package roombook.manage.stay_easy.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<?> handleRegistrationException(RegistrationException ex){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginsException.class)
    public ResponseEntity<?> handleLoginException(LoginsException ex){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldException.class)
    public ResponseEntity<?> handleFieldException(FieldException ex){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FetchException.class)
    public ResponseEntity<?> handleFetchException(FetchException ex){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    // @ExceptionHandler(NotFoundException.class)
    // public ResponseEntity<?> handleNotFoundException(NotFoundException ex){
    //     ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
    //     return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    // }

    // @ExceptionHandler(RentException.class)
    // public ResponseEntity<?> handleRentException(RentException ex){
    //     ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
    //     return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    // }

}