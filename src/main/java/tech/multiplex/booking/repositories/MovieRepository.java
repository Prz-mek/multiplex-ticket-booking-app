package tech.multiplex.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.multiplex.booking.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
