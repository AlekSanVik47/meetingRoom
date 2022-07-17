package meeting_room.repositories;

import meeting_room.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {



	List<User> findAllById(Long meetingId);
	

	User findUserByPhone (String phone);


	User findUserById(Long id);

	User findUserByLogin(String login);
}