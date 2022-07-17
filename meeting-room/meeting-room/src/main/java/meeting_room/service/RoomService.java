package meeting_room.service;

import lombok.RequiredArgsConstructor;
import meeting_room.dto.RoomDto;
import meeting_room.entities.Room;
import meeting_room.exception.ExceedsCapacityException;
import meeting_room.mapper.RoomMapper;
import meeting_room.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class RoomService {
	private final RoomRepository roomRepository;
	private final RoomMapper roomMapper;
	public Optional<Room> getRoomById(Long id){
		return roomRepository.findById(id);
	}

	public boolean capacityCheckService(int roomNumber, int countUser)throws ExceedsCapacityException{
		Room room=roomRepository.findRoomByRoomNumber(roomNumber);
		int roomCapacity=room.getCapacity();
		if (roomCapacity<countUser){
			throw new ExceedsCapacityException();
		}
		return true;
	}

	public Room saveRoom(RoomDto roomDto){
		Room room = roomMapper.dtoToRoom(roomDto);
		room.setRoomNumber(roomDto.getRoomNumber());
		room.setCapacity(room.getCapacity());
		room.setTelevision(roomDto.getTelevision());
		return roomRepository.save(room);
	}
}
