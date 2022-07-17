package meeting_room.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import meeting_room.dto.RoomDto;
import meeting_room.entities.Room;
import meeting_room.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("room")
@RequiredArgsConstructor
@Tag(name = "RoomController", description = "Room Controller API ")
@Validated
public class RoomController {
	private final RoomService roomService;

	@Operation(description = "Добавление комнаты в БД")
	@PostMapping
	public ResponseEntity<Room> saveRoomController(@Parameter(description = "Добавление комнаты для переговоров", required = true)
												   @RequestBody(required = false)RoomDto request){
		return ResponseEntity.ok(roomService.saveRoom(request));
	}
}
