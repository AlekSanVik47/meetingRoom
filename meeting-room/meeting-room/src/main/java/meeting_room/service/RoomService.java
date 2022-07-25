package meeting_room.service;

import lombok.RequiredArgsConstructor;
import meeting_room.dto.RoomDto;
import meeting_room.entities.Room;
import meeting_room.exception.DataNotInDBException;
import meeting_room.exception.ExceedsCapacityException;
import meeting_room.exception.RoomHasMeetingsException;
import meeting_room.mapper.RoomMapper;
import meeting_room.repositories.MeetingRepository;
import meeting_room.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class RoomService {
	private final RoomRepository roomRepository;
	private final RoomMapper roomMapper;

	private final MeetingRepository meetingRepository;

	public Room getRoomById(Long id)throws DataNotInDBException {
		Optional<Room> optionalRoom = Optional.ofNullable(roomRepository.findRoomById(id));
		Room room = optionalRoom.orElseThrow(()-> new DataNotInDBException());
		return room;
	}

	public boolean capacityCheckService(int roomNumber, int countUser) throws ExceedsCapacityException {
		Room room = roomRepository.findRoomByRoomNumber(roomNumber);
		int roomCapacity = room.getCapacity();
		if (roomCapacity < countUser) {
			throw new ExceedsCapacityException();
		}
		return true;
	}

	public Room saveRoom(RoomDto roomDto) {
		Room room = roomMapper.dtoToRoom(roomDto);
		room.setRoomNumber(roomDto.getRoomNumber());
		room.setCapacity(room.getCapacity());
		room.setTelevision(roomDto.getTelevision());
		return roomRepository.save(room);
	}

	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	@ExceptionHandler
	public Room getRoomByRoomNumber(int roomNumber) throws DataNotInDBException{
		if (roomRepository.findRoomByRoomNumber(roomNumber)== null) throw new DataNotInDBException();
		return roomRepository.findRoomByRoomNumber(roomNumber);
	}

	@ExceptionHandler
	public Room roomUpdate(RoomDto dto, Long roomId) throws DataNotInDBException {
		if (roomRepository.existsById(roomId)) {
			Room room = roomMapper.dtoToRoom(dto);
			room.setId(roomId);
			room.setRoomNumber(dto.getRoomNumber());
			room.setCapacity(dto.getCapacity());
			room.setTelevision(dto.getTelevision());
			return roomRepository.saveAndFlush(room);
		}
		throw new DataNotInDBException();
	}

	@ExceptionHandler
	public void roomDelete(Long roomId) throws DataNotInDBException, RoomHasMeetingsException {
		if (!roomRepository.existsById(roomId)) new DataNotInDBException();
		if (!meetingRepository.getMeetingsByRoomId(roomId).isEmpty()) throw new RoomHasMeetingsException();
		roomRepository.deleteById(roomId);
	}

}
