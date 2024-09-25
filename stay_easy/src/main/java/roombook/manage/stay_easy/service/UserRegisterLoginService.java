package roombook.manage.stay_easy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import roombook.manage.stay_easy.dto.Roles;
import roombook.manage.stay_easy.dto.StatusMessage;
import roombook.manage.stay_easy.exception.LoginsException;
import roombook.manage.stay_easy.exception.RegistrationException;
import roombook.manage.stay_easy.model.User;
import roombook.manage.stay_easy.repository.IUserRepository;
import roombook.manage.stay_easy.utils.ValidationChecks;

@Service
public class UserRegisterLoginService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User userRegistrationS(String email, String password, String firstname, String lastname, String role) {
        // TODO Auto-generated method stub
        try {

            //Validation of fields
            if(!ValidationChecks.emailPatternCheck(email)){
                throw new RegistrationException("Please correct email format");
            }
            if(password == "" || password.isEmpty() || password == null){
                throw new RegistrationException("Password field should have some value");
            }
            if(!ValidationChecks.namePatternCheck(firstname)){
                throw new RegistrationException("Firstname field should have some value and alphabets only");
            }

            if(!ValidationChecks.namePatternCheck(lastname)){
                throw new RegistrationException("Lastname field should have some value and alphabets only");
            }

            if (role == null || role == "") {
                role = Roles.CUSTOMER.toString();
            }

            if (!(role.equals(Roles.CUSTOMER.toString())) && !(role.equals(Roles.HOTEL_MANAGER.toString())) && !(role.equals(Roles.ADMIN.toString()))) {
                throw new RegistrationException("Role can be ADMIN, USER, HOTEL_MANAGER");
            }

            if(userRepository.findByEmail(email).isPresent()){
                throw new RegistrationException("Email already exist");
            }
            
            //encrypting password
            String encryptedPassword = passwordEncoder.encode(password);

            User user = new User();
            user.setEmail(email);
            user.setPassword(encryptedPassword);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setRole(role);
            
            userRepository.save(user);

            return user;

            
        } catch (Exception e) {
            // TODO: handle exception
            throw new RegistrationException(e.getMessage());
        }
    }

    public StatusMessage userLoginS(String email, String password) {
        // TODO Auto-generated method stub
        try {

            if(!ValidationChecks.emailPatternCheck(email)){
                throw new LoginsException("Please correct email format, empty or null value not allowed");
            }

            if(password == ""){
                throw new LoginsException("Password header is empty, please pass correct value");
            }

            Optional<User> user = userRepository.findByEmail(email);
            if(!user.isPresent()){
                throw new LoginsException("Email is incorrect, please pass registered email");
            }

            User userVal = user.get();

            if(!passwordEncoder.matches(password, userVal.getPassword())){
                throw new LoginsException("Password is incorrect, please pass correct password");
            }

            return new StatusMessage("Successful login");
            
        } catch (Exception e) {
            throw new LoginsException(e.getMessage());
        }
        
    }
}


