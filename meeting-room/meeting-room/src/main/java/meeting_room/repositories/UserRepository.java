package meeting_room.repositories;

import meeting_room.entities.Meeting;
import meeting_room.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByPhone(String phone);

	User findUserById(Long id);

	List<User> findAllById(Long meetingId);
}
