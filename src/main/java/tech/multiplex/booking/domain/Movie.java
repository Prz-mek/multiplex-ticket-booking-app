package tech.multiplex.booking.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
