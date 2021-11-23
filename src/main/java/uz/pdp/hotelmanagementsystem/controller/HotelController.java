package uz.pdp.hotelmanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelmanagementsystem.entitiy.Hotel;
import uz.pdp.hotelmanagementsystem.repository.HotelRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/hotel")
@RestController
@AllArgsConstructor
public class HotelController {

    private HotelRepository hotelRepository;

    @GetMapping
    public Page<Hotel> getHotels(@RequestParam(name = "start") Integer pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,10);
        return hotelRepository.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public List<Hotel> getHotelsById(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        return optionalHotel.map(Collections::singletonList).orElse(Collections.emptyList());
    }

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        if (!hotelRepository.existsByName(hotel.getName())){
            hotelRepository.save(new Hotel(null, hotel.getName()));
            return "Successfuly saved Hotel Name";
        }else  {
            return "this hotel name is already exists";
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteHotel(@PathVariable Integer id){
        if (hotelRepository.existsById(id)){
            hotelRepository.deleteById(id);
            return "sucessfully deleted";
        }else return "Not found hotel with this id";
    }


    @PutMapping(value = "/{id}")
    public String editHotel(@RequestBody Hotel hotel,@PathVariable Integer id){
        if (hotelRepository.existsById(id)){
            hotelRepository.save(new Hotel(id,hotel.getName()));
            return "succesfully edited";
        }else{
            return "cannot find Hotel with this id";
        }
    }


}
