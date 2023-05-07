package tech.multiplex.booking.domain;

import jakarta.persistence.*;
import lombok.*;
import tech.multiplex.booking.enums.TicketType;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TicketType type;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    public Ticket(TicketType type, Seat seat, Reservation reservation, Screening screening) {
        this.type = type;
        this.seat = seat;
        this.reservation = reservation;
        this.screening = screening;
    }
}
