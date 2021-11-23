package uz.pdp.hotelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelmanagementsystem.entitiy.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    boolean existsByName(String name);
    Hotel findByName(String name);
}
