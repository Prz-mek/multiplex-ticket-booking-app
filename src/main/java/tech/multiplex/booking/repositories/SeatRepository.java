package tech.multiplex.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.multiplex.booking.domain.Seat;

import java.util.List;


@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByRoomId(Long roomId);

    @Query("SELECT s FROM Seat s WHERE s.room.id = :roomId AND s NOT IN (SELECT t.seat FROM Ticket t WHERE t.screening.id = :screeningId)")
    List<Seat> findAvailableSeatsByScreeningIdAndRoomId(@Param("screeningId") Long screeningId, @Param("roomId") Long roomId);

    @Query("SELECT s FROM Seat s WHERE s.room.id = :roomId AND s.row IN :rows AND s IN (SELECT t.seat FROM Ticket t WHERE t.screening.id = :screeningId)")
    List<Seat> findAllNotAvailableSeatsByRowIn(@Param("screeningId") Long screeningId, @Param("roomId") Long roomId, @Param("rows") List<Integer> rows);
}
