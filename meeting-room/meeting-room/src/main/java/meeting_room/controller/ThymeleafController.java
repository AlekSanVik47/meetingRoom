package meeting_room.controller;

import meeting_room.dto.MeetingCreateDto;
import meeting_room.entities.Meeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThymeleafController {
    @GetMapping("/meeting")
    public String getMeeting(MeetingCreateDto dto) {
        Meeting meeting = new Meeting();
        meeting.setStartMeet(dto.getStartMeet());
        meeting.setEndMeet(dto.getEndMeet());
        return "login";
    }
}
