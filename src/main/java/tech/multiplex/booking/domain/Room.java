package tech.multiplex.booking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
