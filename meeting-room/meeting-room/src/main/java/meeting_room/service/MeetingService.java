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

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
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


	public Meeting addMeeting(MeetingDto meetingDto, Long id)throws PeriodCannotBeUsedException, ExceedsCapacityException {
		if (meetingTimeCheckService(meetingDto)) {
			Meeting meeting = meetingMapper.toMeeting(meetingDto);

				List<User> userList = new ArrayList<>();
				userList.add(userRepository.findUserById(id));
				meeting.setStart(LocalDateTime.from(meetingDto.getStart()));
				meeting.setEnd(LocalDateTime.from(meetingDto.getEnd()));
				meeting.setUserList(userList);
				meeting.setRoom(roomRepository.findById(meetingDto.getRoom().getId()).get());
				meetingRepository.save(meeting);
				return meeting;
		}
		throw new PeriodCannotBeUsedException();
	}

	public List<Meeting> getMeetingsService(){
		return meetingRepository.findAll();
	}

	public List<Meeting> getMeetingByUser(Long userId){
		List<Meeting> meetings = meetingRepository.findAll();
		meetings.stream()
				.forEach((meeting -> meeting.getUserList()
						.forEach(User::getId)));
		System.out.println(meetings);
		return meetings;

	}

	public boolean meetingTimeCheckService(MeetingDto dto){
		List<Meeting> meetingList= getMeetingsService();
		for (Meeting m: meetingList) {
			if ((m.getEnd().isBefore(ChronoLocalDateTime.from(dto.getEnd()))||
					m.getEnd().isEqual(ChronoLocalDateTime.from(dto.getStart()))) &&
					(m.getStart().isAfter(ChronoLocalDateTime.from(dto.getEnd())))||
					m.getStart().isEqual(ChronoLocalDateTime.from(dto.getEnd()))){
				return true;
			}
		}
		return false;
	}

}
