package meeting_room.service;

import lombok.RequiredArgsConstructor;
import meeting_room.dto.UserDto;
import meeting_room.entities.User;
import meeting_room.exception.UserExistsException;
import meeting_room.exception.UserHasMeetingsException;
import meeting_room.exception.UserNotFoundException;
import meeting_room.mapper.UserMapper;
import meeting_room.repositories.MeetingRepository;
import meeting_room.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	private final MeetingRepository meetingRepository;


	@Override
	public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
		Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByPhone(phone));
		User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
		return (UserDetails) user;
	}


	@Override
	public User findByUserPhone(String phone) {
		return userRepository.findUserByPhone(phone);
	}

	@ExceptionHandler
	@Override
	public User saveUser(UserDto userDto)throws UserExistsException {
		User user = userMapper.userDtoToUser(userDto);
		if (findByUserPhone(userDto.getPhone()) != null) {
			throw  new UserExistsException();
		}
		user.setLogin(userDto.getLogin());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setName(userDto.getName());
		user.setSurname(user.getSurname());
		user.setPatronymic(user.getPatronymic());
		user.setPhone(user.getPhone());
		user.setPosition(user.getPosition());
		return userRepository.save(user);
	}

	@ExceptionHandler
	public User userUpdate(UserDto userDto, Long userId)throws UserNotFoundException {
		if (userRepository.existsById(userId)) {
			User user = userMapper.userDtoToUser(userDto);
			user.setId(userDto.getId());
			user.setLogin(userDto.getLogin());
			user.setLogin(user.getLogin());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			user.setName(userDto.getName());
			user.setSurname(userDto.getSurname());
			user.setPatronymic(userDto.getPatronymic());
			user.setPhone(userDto.getPhone());
			user.setPosition(userDto.getPosition());
			return userRepository.saveAndFlush(user);
		}
		throw  new UserNotFoundException();
	}
	public List<User> getAllUsersByMeeting(Long meetingId){
		return userRepository.findAllById(meetingId);
	}

	public List<User> getAllUsers(){
		List<User> userList = userRepository.findAll();
		return userList;
	}
	@ExceptionHandler
	public User getUser(Long userId)throws UserNotFoundException {
		return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
	}
	@ExceptionHandler
     public void deleteUser(Long userId)throws UserNotFoundException, UserHasMeetingsException {
		if (!userRepository.existsById(userId)) throw new UserNotFoundException();
		if (!meetingRepository.getUserMeetings(userId).isEmpty()) throw new UserHasMeetingsException();
         userRepository.deleteById(userId);
	 }
}
