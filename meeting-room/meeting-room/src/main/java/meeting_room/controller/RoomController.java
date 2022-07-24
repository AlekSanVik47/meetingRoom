package meeting_room.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import meeting_room.dto.RoomDto;
import meeting_room.dto.UserDto;
import meeting_room.entities.Room;
import meeting_room.entities.User;
import meeting_room.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("room")
@RequiredArgsConstructor
@Tag(name = "RoomController", description = "Room Controller API ")
@Validated
public class RoomController {
	private final RoomService roomService;

	@Operation(description = "Добавление комнаты в БД")
	@PostMapping(produces = {"application/json"},
			consumes = {"application/json"})
	public ResponseEntity<Room> saveRoomController(@Parameter(description = "Добавление комнаты для переговоров", required = true)
												   @RequestBody(required = false)RoomDto request){
		return ResponseEntity.ok(roomService.saveRoom(request));
	}

	@Operation(description = "Список комнат")
	@GetMapping(produces = {"application/json"},
	value = "{list}")
	public ResponseEntity<List<Room>> getAllRoomsController(@Parameter(description = "Получение списка комнат")
												  @RequestParam String list ){
		List<Room> roomList = roomService.getAllRooms();
		return ResponseEntity.ok(roomList);
	}
	@Operation(description = "Комната по номеру")
	@GetMapping(value = "{roomNumber}")
	public ResponseEntity<Room> getRoomByRoomNumberController(@Parameter(description = "Получение комнаты по номеру")
												  @PathVariable int roomNumber ){
		return ResponseEntity.ok(roomService.getRoomByRoomNumber(roomNumber));
	}
	@Operation(description = "Обновление данных комнаты в БД")
	@PutMapping(value = "{roomId}")
	public ResponseEntity<Room> roomUpdateController(@Parameter(description = "Обновление данных комнаты", required = true)
													 @RequestBody(required = false) RoomDto request,
													 @RequestParam("roomId") Long roomId) {
		return ResponseEntity.ok(roomService.roomUpdate(request,roomId));
	}
	@Operation(description = "Удаление комнаты по ID")
	@DeleteMapping(value = "{roomId}")
	public ResponseEntity<Object> deleteRoomController(@Parameter(description = "Удаление комнаты", required = true)
													   @PathVariable(value = "userId") Long roomId){
		roomService.roomDelete(roomId);
		return ResponseEntity.noContent().build();
	}
}
