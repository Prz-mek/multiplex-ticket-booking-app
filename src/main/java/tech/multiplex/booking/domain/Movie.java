package tech.multiplex.booking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "movie_id")
    private List<Screening> screenings;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private Integer durationInMinutes;
}
