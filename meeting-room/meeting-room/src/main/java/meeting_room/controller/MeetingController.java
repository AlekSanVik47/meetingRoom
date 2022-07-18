package meeting_room.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import meeting_room.dto.MeetingCreateDto;
import meeting_room.dto.MeetingDto;
import meeting_room.entities.Meeting;
import meeting_room.exception.ExceedsCapacityException;
import meeting_room.exception.PeriodCannotBeUsedException;
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
	@RequestBody(required = false) MeetingCreateDto request)
			throws PeriodCannotBeUsedException, ExceedsCapacityException{
		return ResponseEntity.ok(meetingService.addMeeting(request));

	}



	@Operation(description = "Получение списка митингов пользователя")
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<List<Meeting>> getMeetingByUserController(@Parameter(description = "Список митингов пользователя")
																	@RequestParam Long userId, Long roomId) {
		List<Meeting> meetings = meetingService.getMeetingByUser(userId);
		return ResponseEntity.ok(meetings);
	}
}
