package uz.pdp.hotelmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelmanagementsystem.entitiy.Hotel;
import uz.pdp.hotelmanagementsystem.payload.RoomDto;
import uz.pdp.hotelmanagementsystem.entitiy.Room;
import uz.pdp.hotelmanagementsystem.repository.HotelRepository;
import uz.pdp.hotelmanagementsystem.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;
    @GetMapping
    public List<Room> getRooms(@RequestParam(value = "start") Integer start){
        Pageable pageable = PageRequest.of(start,10);
        Page<Room> page = roomRepository.findAll(pageable);
        if (page.isEmpty()){
            return new ArrayList<>();
        }
        Stream<Room> roomStream = page.get();
        return roomStream.collect(Collectors.toList());
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
        Hotel hotel = new Hotel(null, roomDto.getHotelName());
        if (hotelRepository.existsByName(roomDto.getHotelName())){
            Hotel hotelByName = hotelRepository.findByName(roomDto.getHotelName());
            Room room = new Room(null,roomDto.getRoomNumber(), roomDto.getFloorNumber(), roomDto.getSize(),hotelByName);
            roomRepository.save(room);
            return "Room saved Successfully";
        }else {
            Hotel savedHotel = hotelRepository.save(hotel);
            Room room = new Room(null,roomDto.getRoomNumber(), roomDto.getFloorNumber(), roomDto.getSize(),savedHotel);
            roomRepository.save(room);
            return "Hotel and room saved succesfully";
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteById(@PathVariable Integer id){
        boolean existsById = roomRepository.existsById(id);
        if (existsById){
            roomRepository.deleteById(id);
            return "successfully deleted";
        }else{
            return "room not found with this id";
        }
    }

    @PutMapping(value = "/{id}")
    public String editRoomById(@PathVariable Integer id,@RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            Room room = optionalRoom.get();
            room.setRoomNumber(roomDto.getRoomNumber());
            room.setSize(roomDto.getSize());
            Hotel hotel = room.getHotel();
            hotel.setName(roomDto.getHotelName());
            room.setHotel(hotel);
            roomRepository.save(room);
            return "succesfully edited";
        }else return "room not found with this id";
    }
}
