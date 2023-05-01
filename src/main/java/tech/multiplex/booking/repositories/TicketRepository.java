package tech.multiplex.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.multiplex.booking.domain.Ticket;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
