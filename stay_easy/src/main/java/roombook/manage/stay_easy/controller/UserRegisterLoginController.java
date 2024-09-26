package roombook.manage.stay_easy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.protocol.AuthenticationProvider;

import roombook.manage.stay_easy.dto.StatusMessage;
import roombook.manage.stay_easy.dto.StatusToken;
import roombook.manage.stay_easy.model.User;
import roombook.manage.stay_easy.security.JwtHelper;
import roombook.manage.stay_easy.service.MyUserDetailService;
import roombook.manage.stay_easy.service.UserRegisterLoginService;

@RestController
@RequestMapping("/user")
public class UserRegisterLoginController {

    @Autowired
    private UserRegisterLoginService userRegisterLoginService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private MyUserDetailService myUserDetailService;

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
    public ResponseEntity<StatusToken> userLogin(@RequestHeader(value = "email", defaultValue = "") String email, @RequestHeader(value = "password", defaultValue = "") String password) {
        //TODO: process POST request

        StatusToken response;

        this.doAuthenticate(email, password);
        StatusMessage resp = userRegisterLoginService.userLoginS(email, password);

        if(resp.getStatus() == "Successful login"){
            UserDetails userDetails = myUserDetailService.loadUserByUsername(email);
            String token = this.jwtHelper.generateToken(userDetails);

            response = StatusToken.builder()
            .token(token)
            .status("Successful login & token generated").build();

        }else{

            response = StatusToken.builder()
            .token("Cannot generate token")
            .status("Falied to login & cannot generate token").build();

        }


        return new ResponseEntity<>(response , HttpStatus.OK);
    }


    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
