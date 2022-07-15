package meeting_room.repositories;

import meeting_room.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
	List<Meeting> findAllByUserId(Long userId);
}