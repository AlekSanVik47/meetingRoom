package meeting_room.service;

import meeting_room.dto.UserDto;
import meeting_room.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {
	Optional<User> findByUserPhone (String phone);
	@Transactional
	boolean saveUser(UserDto userDto);
}
