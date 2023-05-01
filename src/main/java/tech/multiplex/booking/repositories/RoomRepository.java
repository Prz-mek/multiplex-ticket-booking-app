package tech.multiplex.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.multiplex.booking.domain.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
