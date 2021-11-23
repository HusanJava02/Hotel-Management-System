package uz.pdp.hotelmanagementsystem.payload;

import lombok.Data;
import uz.pdp.hotelmanagementsystem.entitiy.Hotel;

import javax.persistence.*;

@Data
public class RoomDto {
    private Integer roomNumber;
    private Integer size;
    private Integer floorNumber;
    private String hotelName;
}
