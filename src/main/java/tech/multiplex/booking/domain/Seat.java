package tech.multiplex.booking.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "seat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "seats_row")
    private Integer row;

    @Column(name = "number_in_row")
    private Integer numberInRow;
}
