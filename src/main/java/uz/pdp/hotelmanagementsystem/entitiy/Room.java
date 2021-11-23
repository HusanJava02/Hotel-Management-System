package uz.pdp.hotelmanagementsystem.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_room_number_floorNumber",columnNames = {"roomNumber","floorNumber"})})
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer roomNumber;

    @Column(nullable = false)
    private Integer floorNumber;

    @Column(nullable = false)
    private Integer size;

    @ManyToOne(optional = false)
    private Hotel hotel;
}
