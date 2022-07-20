package meeting_room.service;

import lombok.RequiredArgsConstructor;
import meeting_room.dto.MeetingCreateDto;
import meeting_room.dto.MeetingDto;
import meeting_room.entities.Meeting;
import meeting_room.entities.Room;
import meeting_room.entities.User;
import meeting_room.exception.*;
import meeting_room.mapper.MeetingMapper;
import meeting_room.mapper.RoomMapper;
import meeting_room.mapper.UserMapper;
import meeting_room.repositories.MeetingRepository;
import meeting_room.repositories.RoomRepository;
import meeting_room.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;

    private final RoomMapper roomMapper;
    private final UserMapper userMapper;

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserServiceImpl userService;
    private final int MIN_MEET_INTERVAL = 30;

    @ExceptionHandler
    public Meeting addMeeting(MeetingCreateDto meetingCreateDto) {
        Meeting meeting = new Meeting();
        ZonedDateTime startMeet = installStartTime(meetingCreateDto);
        ZonedDateTime endMeet = installEndTime(meetingCreateDto);

        User owner = userRepository.findUserById(meetingCreateDto.getOwnerIDId());
//        User user = userService.getUser(meetingCreateDto.getOwnerIDId());
        List<User> users = new ArrayList<>();
        users.add(owner);
        Room room = roomRepository.findRoomById(meetingCreateDto.getRoomId());
        if (meetingTimeCheckService(meetingCreateDto)
                && checkMinMeetInterval(meetingCreateDto)) {
            meeting.setStartMeet(startMeet);
            meeting.setEndMeet(endMeet);
            meeting.setRoom(room);
            meeting.setOwnerID(owner);
            meeting.setUserList(users);
        }
        System.out.println(meetingRepository.save(meeting));
        return meeting;
    }

    public List<Meeting> getMeetingsService() {
        return meetingRepository.findAll();
    }

    public List<Meeting> getMeetingByUser(Long userId) {
        List<Meeting> meetings = meetingRepository.findAll();
        meetings.stream()
                .forEach((meeting -> meeting.getUserList()
                        .forEach(User::getId)));
        System.out.println(meetings);
        return meetings;

    }

    @ExceptionHandler
    public boolean meetingTimeCheckService(MeetingCreateDto dto) throws PeriodCannotBeUsedException {
        List<Meeting> meetingList = getMeetingsService();
        if (meetingList.size() == 0) {
            if (dto.getStartMeet().isBefore(dto.getEndMeet())) {
                System.out.println(dto.getStartMeet().isBefore(dto.getStartMeet()));
                return true;
            }
        }
        if (meetingList.size() > 0) {
            for (Meeting m : meetingList) {
                if ((m.getStartMeet().isBefore(dto.getEndMeet())) ||
                        m.getEndMeet().isEqual(dto.getStartMeet()) &&
                                (m.getStartMeet().isAfter(dto.getEndMeet()) ||
                                        m.getStartMeet().isEqual(dto.getEndMeet()))) {
                    return true;
                }
            }
        }
        throw new PeriodCannotBeUsedException();
    }

    public int getMeetingTime(MeetingCreateDto meetingDto) {
        ZonedDateTime startMeeting = meetingDto.getStartMeet();
        ZonedDateTime endMeeting = meetingDto.getEndMeet();
        Duration difference = Duration.between(startMeeting, endMeeting);
        int meetTime = (int) difference.toMinutes();
        return meetTime;
    }

    public String getTimeDateFormatting(ZonedDateTime dateTime) {
        dateTime = ZonedDateTime.of(LocalDateTime.from(dateTime), dateTime.getZone());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);

    }

    public ZonedDateTime installStartTime(MeetingCreateDto meetingDto) {
        LocalDate date = meetingDto.getStartMeet().toLocalDate();
        LocalTime time = meetingDto.getStartMeet().toLocalTime();
        ZonedDateTime startTime = ZonedDateTime.of(date, time, ZoneId.systemDefault());
        return startTime;
    }

    public ZonedDateTime installEndTime(MeetingCreateDto meetingDto) {
        LocalDate date = meetingDto.getEndMeet().toLocalDate();
        LocalTime time = meetingDto.getEndMeet().toLocalTime();
        ZonedDateTime endTime = ZonedDateTime.of(date, time, ZoneId.systemDefault());
        return endTime;
    }

    @ExceptionHandler
    public boolean checkMinMeetInterval(MeetingCreateDto meetingDto) throws MinTimeIntervalException {
        Duration intervalMeeting = Duration.between(meetingDto.getStartMeet(), meetingDto.getEndMeet());
        int minutes = Math.toIntExact(intervalMeeting.toMinutes());
        if (meetingDto.getStartMeet().isBefore(meetingDto.getEndMeet()) &&
                minutes >= MIN_MEET_INTERVAL) {
            return true;
        }
        throw new MinTimeIntervalException();
    }

    public Meeting addUsersToMeeting(MeetingDto dto, Long... usersId) throws UserNotFoundException, MeetingNotFoundException {
        try {
            Meeting meeting = meetingRepository.findMeetingById(dto.getId());
            if (meetingRepository.existsById(dto.getId()) && meeting != null) {
                List<User> listParticipants = new ArrayList<>();
                for (Long u : usersId) {
                    if (userRepository.existsById(u)) listParticipants.add(userRepository.findUserById(u));
                }
                meeting.setUserList(listParticipants);
                return meetingRepository.save(meeting);
            }
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (MeetingNotFoundException e) {
            throw new MeetingNotFoundException();
        }
        return null;
    }

}
