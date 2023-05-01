package tech.multiplex.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.multiplex.booking.domain.Seat;


@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
