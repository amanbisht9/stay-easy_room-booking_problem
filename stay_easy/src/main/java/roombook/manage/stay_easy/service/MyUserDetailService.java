package roombook.manage.stay_easy.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import roombook.manage.stay_easy.exception.FieldException;
import roombook.manage.stay_easy.model.User;
import roombook.manage.stay_easy.repository.IUserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if(!user.isPresent()){
                throw new UsernameNotFoundException("Username not found");
            }
            return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.get().getRole()))
                );

        } catch (Exception e) {
            throw new FieldException(e.getMessage());
        }
    }
    
}