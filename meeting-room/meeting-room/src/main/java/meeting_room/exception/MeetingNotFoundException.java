package meeting_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class MeetingNotFoundException extends RuntimeException {
    private static final String MEETING_NOT_FOUND = "Встреча не найдена!";
    public MeetingNotFoundException() {
        super(MEETING_NOT_FOUND);
    }
}
