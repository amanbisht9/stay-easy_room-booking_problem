package roombook.manage.stay_easy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import roombook.manage.stay_easy.model.Hotel;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Integer>{
    
}
