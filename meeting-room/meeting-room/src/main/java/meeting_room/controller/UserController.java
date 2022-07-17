package meeting_room.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import meeting_room.dto.UserDto;
import meeting_room.entities.User;
import meeting_room.exception.DataNotInDBException;
import meeting_room.exception.Response;
import meeting_room.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "UserController", description = "User Controller API")
@Validated
public class UserController {
	@Autowired
	private final UserServiceImpl userService;

	@Operation(description = "Сохранение пользователя в БД")
	@PostMapping
	public ResponseEntity <User> saveUserController(@Parameter(description = "Сохранение пользователя",required = true)
													@RequestBody(required = false) UserDto request){
		return ResponseEntity.ok(userService.saveUser(request));
	}

	@Operation(description = "Получение списка пользователей")
	@GetMapping(value = "{list}",
			produces = {"application/json"})
	public ResponseEntity<List<User>> getAllUsersController(@Parameter(description = "Список пользователей")
															@PathVariable(value = "list")String list){
		List<User> userList = userService.getAllUsers();
		return ResponseEntity.ok(userList);
	}

	@Operation(description = "Получение списка участников митинга")
	@GetMapping(produces = {"application/json"})
	public ResponseEntity <List<User>> getAllUsersByMeetingController(@Parameter(description = "Список участников митинга")
																	  @RequestParam Long meetingId){
		List<User> users=userService.getAllUsersByMeeting(meetingId);
		return ResponseEntity.ok(users);
	}

	@Operation(description = " Получение пользователя по номеру телефона")
	@GetMapping(value = "/{phone}",
			produces = {"application/json"})
	public ResponseEntity<User> getDBUserByPhoneController(@Parameter(description = "Поиск пользователя по номеру телефона",required = true)
																   @RequestParam String phone)
			throws DataNotInDBException {
		return  ResponseEntity.ok(userService.getDBUserByPhone(phone));
	}
	@ExceptionHandler({DataNotInDBException.class})
	public Response handleException(DataNotInDBException e){
		return new Response(e.getMessage());

	}
}
