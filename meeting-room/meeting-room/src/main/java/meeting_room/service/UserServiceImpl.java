package meeting_room.service;

import lombok.RequiredArgsConstructor;
import meeting_room.dto.UserDto;
import meeting_room.entities.User;
import meeting_room.mapper.UserMapper;
import meeting_room.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
		Optional<User> optionalUser=userRepository.findUserByPhone(phone);
		User user = optionalUser.orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден"));
		return (UserDetails) user;
	}


	@Override
	public Optional<User> findByUserPhone(String phone) {
		return userRepository.findUserByPhone(phone);
	}

	@Override
	public boolean saveUser(UserDto userDto) {
		User user= userMapper.toUser(userDto);
		user.setLogin(userDto.getLogin());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setName(userDto.getName());
		user.setSurname(user.getSurname());
		user.setPatronymic(user.getPatronymic());
		user.setPhone(user.getPhone());
		user.setPosition(user.getPosition());
		userRepository.save(user);
		return true;
	}

	public List<User> getAllUsersByMeeting(Long meetingId){
		return userRepository.findAllByMeetingId(meetingId);
	}
}
