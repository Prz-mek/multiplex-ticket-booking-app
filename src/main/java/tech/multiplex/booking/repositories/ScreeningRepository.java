package tech.multiplex.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.multiplex.booking.domain.Screening;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    List<Screening> findAllByStartTimeBetweenOrderByMovieTitleAscStartTime(LocalDateTime from, LocalDateTime to);
}
