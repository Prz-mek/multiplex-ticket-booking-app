package tech.multiplex.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.multiplex.booking.domain.Ticket;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByScreeningId(Long screeningId);

    boolean existsByScreeningIdAndSeatIdIn(Long screeningId, List<Long> seatIds);
}
