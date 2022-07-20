package meeting_room.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import meeting_room.dto.MeetingCreateDto;
import meeting_room.dto.MeetingDto;
import meeting_room.entities.Meeting;
import meeting_room.exception.*;
import meeting_room.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("meeting")
@RequiredArgsConstructor
@Tag(name = "MeetingController", description = "Meeting Controller API ")
@Validated
public class MeetingController {
	@Autowired
	private final MeetingService meetingService;


	@Operation(description = "Добавление митинга")
	@PostMapping
	public ResponseEntity<Meeting> addMeetingController(@Parameter(description = "Добавление митинга", required = true)
	@RequestBody(required = false) MeetingCreateDto request, boolean exception)
			throws UserNotFoundException, PeriodCannotBeUsedException, ExceedsCapacityException, MinTimeIntervalException{
		try {
			return ResponseEntity.ok(meetingService.addMeeting(request));
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException();
		} catch (PeriodCannotBeUsedException e) {
			throw new PeriodCannotBeUsedException();
		} catch (ExceedsCapacityException e) {
			throw new ExceedsCapacityException();
		} catch (MinTimeIntervalException e) {
			throw new MinTimeIntervalException();
		}
	}

	@Operation(description = "Получение списка митингов пользователя")
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<List<Meeting>> getMeetingByUserController(@Parameter(description = "Список митингов пользователя")
																	@RequestParam Long userId, Long roomId) {
		List<Meeting> meetings = meetingService.getMeetingByUser(userId);
		return ResponseEntity.ok(meetings);
	}
	@Operation(description = "Добавление участников")
	@PutMapping
	public ResponseEntity<Meeting> addUsersToMeetingController(@Parameter(description = "Добавление участников", required = true)
															   @RequestBody(required = false) MeetingDto request, Long user1, Long user2)
			throws UserNotFoundException, MeetingNotFoundException {
		return ResponseEntity.ok(meetingService.addUsersToMeeting(request, 1L, 1L, 2L));
	}
}
