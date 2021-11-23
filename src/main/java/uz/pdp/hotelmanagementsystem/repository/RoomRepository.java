package uz.pdp.hotelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelmanagementsystem.entitiy.Hotel;
import uz.pdp.hotelmanagementsystem.entitiy.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Room findByHotel_Name(String hotel_name);
}
