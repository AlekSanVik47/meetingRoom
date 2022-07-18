package meeting_room.service;

import lombok.RequiredArgsConstructor;
import meeting_room.dto.MeetingDto;
import meeting_room.entities.Meeting;
import meeting_room.entities.User;
import meeting_room.exception.ExceedsCapacityException;
import meeting_room.exception.MinTimeIntervalException;
import meeting_room.exception.PeriodCannotBeUsedException;
import meeting_room.exception.UserNotFoundException;
import meeting_room.mapper.MeetingMapper;
import meeting_room.repositories.MeetingRepository;
import meeting_room.repositories.RoomRepository;
import meeting_room.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class MeetingService {
	private final MeetingRepository meetingRepository;
	private final MeetingMapper meetingMapper;

	private final UserRepository userRepository;
	private final RoomRepository roomRepository;
	private final UserServiceImpl userService;
	private final int MIN_MEET_INTERVAL = 30;


	public Meeting addMeeting(MeetingDto meetingDto, Long id)
			throws UserNotFoundException, PeriodCannotBeUsedException, ExceedsCapacityException,MinTimeIntervalException{
		Meeting meeting = meetingMapper.toMeeting(meetingDto);
		ZonedDateTime start = installStartTime(meetingDto);
		ZonedDateTime end = installEndTime(meetingDto);
		User user = userService.getUser(id);
		try {
			if (meetingTimeCheckService(meetingDto) && checkMinMeetInterval(meetingDto)) {
				List<User> userList = new ArrayList<>();
				userList.add(user);
				meeting.setStart(start);
				meeting.setEnd(end);
				meeting.setUserList(userList);
				meeting.setRoom(roomRepository.findById(meetingDto.getRoom().getId()).get());
			}
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException();
		} catch (PeriodCannotBeUsedException e){
			throw new PeriodCannotBeUsedException();
		} catch (ExceedsCapacityException e){
			throw new ExceedsCapacityException();
		} catch (MinTimeIntervalException e){
			throw new MinTimeIntervalException();
		}
		return meetingRepository.save(meeting);
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

	public boolean meetingTimeCheckService(MeetingDto dto) throws PeriodCannotBeUsedException{
		List<Meeting> meetingList= getMeetingsService();
		for (Meeting m: meetingList) {
			if ((m.getEnd().isBefore(dto.getEnd()))||
					m.getEnd().isEqual(dto.getStart()) &&
					(m.getStart().isAfter(dto.getEnd())||
					m.getStart().isEqual(dto.getEnd()))){
				return true;
			}
		}
		throw new PeriodCannotBeUsedException();
	}
	public int getMeetingTime(MeetingDto meetingDto){
		ZonedDateTime startMeeting = meetingDto.getStart();
		ZonedDateTime endMeeting = meetingDto.getEnd();
		Duration difference = Duration.between(startMeeting,endMeeting );
				int meetTime = (int) difference.toMinutes();
		return meetTime;
	}
	public String getTimeDateFormatting (ZonedDateTime dateTime){
		dateTime=ZonedDateTime.of(LocalDateTime.from(dateTime), dateTime.getZone());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dateTime.format(formatter);

	}

	public ZonedDateTime installStartTime(MeetingDto meetingDto){
		LocalDate date = meetingDto.getStart().toLocalDate();
		LocalTime time = meetingDto.getStart().toLocalTime();
		ZonedDateTime startTime = ZonedDateTime.of(date, time, ZoneId.systemDefault());
		return startTime;
	}

	public ZonedDateTime installEndTime(MeetingDto meetingDto){
		LocalDate date = meetingDto.getStart().toLocalDate();
		LocalTime time = meetingDto.getStart().toLocalTime();
		ZonedDateTime endTime = ZonedDateTime.of(date, time, ZoneId.systemDefault());
		return endTime;
	}
	public  boolean checkMinMeetInterval (MeetingDto meetingDto)throws MinTimeIntervalException {
		Duration intervalMeeting = Duration.between(meetingDto.getStart(), meetingDto.getEnd());
		int minutes = Math.toIntExact(intervalMeeting.toMinutes());
		if (meetingDto.getStart().isBefore(meetingDto.getEnd())&&
				minutes >= MIN_MEET_INTERVAL){
			return true;
		}
		throw new MinTimeIntervalException();
	}


}
