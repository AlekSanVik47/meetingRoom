package meeting_room.service;

import meeting_room.dto.UserDto;
import meeting_room.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService extends UserDetailsService {
	User getDBUserByPhone(String phone);
	@Transactional
	User saveUser(UserDto userDto);
}
