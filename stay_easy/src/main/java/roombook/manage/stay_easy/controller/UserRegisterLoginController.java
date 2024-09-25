package roombook.manage.stay_easy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import roombook.manage.stay_easy.dto.StatusMessage;
import roombook.manage.stay_easy.model.User;
import roombook.manage.stay_easy.service.UserRegisterLoginService;

@RestController
@RequestMapping("/user")
public class UserRegisterLoginController {

    @Autowired
    private UserRegisterLoginService userRegisterLoginService;

    @PostMapping("/register")
    public ResponseEntity<User> userRegistration(@RequestBody User entity) {
        //TODO: process POST request
        String email = entity.getEmail();
        String password = entity.getPassword();
        String firstname = entity.getFirstname();
        String lastname = entity.getLastname();
        String role = entity.getRole();

        User resp = userRegisterLoginService.userRegistrationS(email, password, firstname, lastname, role);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<StatusMessage> userLogin(@RequestHeader(value = "email", defaultValue = "") String email, @RequestHeader(value = "password", defaultValue = "") String password) {
        //TODO: process POST request
        StatusMessage resp = userRegisterLoginService.userLoginS(email, password);
        return new ResponseEntity<>(resp , HttpStatus.OK);
    }

}
