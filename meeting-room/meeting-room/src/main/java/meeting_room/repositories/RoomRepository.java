package meeting_room.repositories;

import meeting_room.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
	Optional<Room> findRoomByRoomNumber(int roomNumber);
}