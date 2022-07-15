package meeting_room.service;

import lombok.RequiredArgsConstructor;
import meeting_room.dto.MeetingDto;
import meeting_room.entities.Meeting;
import meeting_room.entities.Room;
import meeting_room.entities.User;
import meeting_room.exception.ExceedsCapacityException;
import meeting_room.exception.PeriodCannotBeUsedException;
import meeting_room.mapper.MeetingMapper;
import meeting_room.repositories.MeetingRepository;
import meeting_room.repositories.RoomRepository;
import meeting_room.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class MeetingService {
	private final MeetingRepository meetingRepository;
	private final MeetingMapper meetingMapper;
	private final UserRepository userRepository;

	private final RoomRepository roomRepository;

	private final RoomService roomService;

	public Meeting addMeeting(MeetingDto meetingDto, Long id)throws PeriodCannotBeUsedException, ExceedsCapacityException {
		if (meetingTimeCheckService(meetingDto)) {
			Meeting meeting = meetingMapper.toMeeting(meetingDto);
				List<User> userList = new ArrayList<>();
				userList.add(userRepository.findUserById(id));
				Optional<Room> room = roomService.getRoomById(meetingDto.getRoom().getId());
				meeting.setStart(meetingDto.getStart());
				meeting.setEnd(meetingDto.getEnd());
				meeting.setUserList(userList);
				meeting.setRoom(room);
				meetingRepository.save(meeting);
				return meeting;
		}
		throw new PeriodCannotBeUsedException("Период уже используется!");
	}

	public List<Meeting> getMeetingsService(){
		return meetingRepository.findAll();
	}

	public List<Meeting> getMeetingByUser(Long userId){
		return  meetingRepository.findAllByUserId(userId);
	}

	public boolean meetingTimeCheckService(MeetingDto dto){
		List<Meeting> meetingList= getMeetingsService();
		for (Meeting m: meetingList) {
			if ((m.getEnd().isBefore(dto.getStart())||
					m.getEnd().isEqual(dto.getStart())) &&
					(m.getStart().isAfter(dto.getEnd()))||
					m.getStart().isEqual(dto.getEnd())){
				return true;
			}
		}
		return false;
	}

}
