package meeting_room.service;

import lombok.RequiredArgsConstructor;
import meeting_room.dto.MeetingDto;
import meeting_room.entities.Meeting;
import meeting_room.entities.Room;
import meeting_room.entities.User;
import meeting_room.exception.ExceedsCapacityException;
import meeting_room.exception.PeriodCannotBeUsedException;
import meeting_room.exception.UserNotFoundException;
import meeting_room.mapper.MeetingMapper;
import meeting_room.repositories.MeetingRepository;
import meeting_room.repositories.RoomRepository;
import meeting_room.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
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

	private final UserServiceImpl userService;


	public Meeting addMeeting(MeetingDto meetingDto, Long id)
			throws PeriodCannotBeUsedException, ExceedsCapacityException, UserNotFoundException {
		User user = userService.getUser(id);
		if (meetingTimeCheckService(meetingDto)) {
			Meeting meeting = meetingMapper.toMeeting(meetingDto);
			List<User> userList = new ArrayList<>();
				userList.add(user);
				meeting.setStart(meetingDto.getStart());
				meeting.setEnd(meetingDto.getEnd());
				meeting.setUserList(userList);
				meeting.setRoom(roomRepository.findById(meetingDto.getRoom().getId()).get());
				return meetingRepository.save(meeting);
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
			if ((m.getEnd().isBefore(dto.getEnd()))||
					m.getEnd().isEqual(dto.getStart()) &&
					(m.getStart().isAfter(dto.getEnd())||
					m.getStart().isEqual(dto.getEnd()))){
				return true;
			}
		}
		return false;
	}
	public int getMeetingTime(ZonedDateTime startMeeting, ZonedDateTime endMeeting){
		Duration difference = Duration.between(startMeeting,endMeeting );
				int meetTime = (int) difference.toMinutes();
		return meetTime;
	}
	public String getTimeDateFormatting (ZonedDateTime dateTime){
		dateTime=ZonedDateTime.of(LocalDateTime.from(dateTime), dateTime.getZone());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dateTime.format(formatter);

	}

	public ZonedDateTime installDataTime(LocalDate date, LocalTime time){
		ZonedDateTime dateTime = ZonedDateTime.of(date, time, ZoneId.systemDefault());
		return dateTime;
	}
}
