package meeting_room.repositories;

import meeting_room.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByPhone(String phone);

	User findUserById(Long id);

	List<User> findAllById(Long meetingId);
}
