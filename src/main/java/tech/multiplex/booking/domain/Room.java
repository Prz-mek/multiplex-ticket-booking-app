package tech.multiplex.booking.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;


    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Screening> screenings;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Seat> seats;
}
