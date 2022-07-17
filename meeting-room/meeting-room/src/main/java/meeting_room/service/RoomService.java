package meeting_room.service;

import lombok.RequiredArgsConstructor;
import meeting_room.entities.Room;
import meeting_room.exception.ExceedsCapacityException;
import meeting_room.repositories.RoomRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class RoomService {
	private final RoomRepository roomRepository;
	public Optional<Room> getRoomById(Long id){
		return roomRepository.findById(id);
	}

	private boolean capacityCheckService(int roomNumber, int countUser)throws ExceedsCapacityException{
		Room room=roomRepository.findRoomByRoomNumber(roomNumber);
		int roomCapacity=room.getCapacity();
		if (roomCapacity<countUser){
			throw new ExceedsCapacityException();
		}

		return true;
	}
}
