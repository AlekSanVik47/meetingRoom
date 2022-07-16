package meeting_room.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import meeting_room.entities.User;
import meeting_room.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "UserController", description = "User Controller API")
@Validated
public class UserController {
	@Autowired
	private final UserServiceImpl userService;

	@Operation(description = "Получение списка участников митинга")
	@GetMapping(produces = {"application/json"})
	public ResponseEntity <List<User>> getAllUsersByMeetingController(@Parameter(description = "Список участников митинга")
																		  @RequestParam Long meetingId){
		List<User> users=userService.getAllUsersByMeeting(meetingId);
		return ResponseEntity.ok(users);
	}
}
