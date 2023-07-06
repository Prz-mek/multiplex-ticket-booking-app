package tech.multiplex.booking.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @OneToMany
    @JoinColumn(name = "reservation_id")
    private List<Ticket> tickets;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    public Reservation(Screening screening, String firstName, String secondName, LocalDateTime expirationTime) {
        this.screening = screening;
        this.firstName = firstName;
        this.secondName = secondName;
        this.expirationTime = expirationTime;
    }
}
