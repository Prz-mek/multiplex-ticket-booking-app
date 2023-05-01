package tech.multiplex.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.multiplex.booking.domain.Screening;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
