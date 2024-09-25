package roombook.manage.stay_easy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import roombook.manage.stay_easy.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);
    
}
