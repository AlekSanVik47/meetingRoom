package meeting_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class LateLimitException extends RuntimeException{
    private static final String LATE_LIMIT = "Дата встречи позже установленного ограничения!";
    public LateLimitException() {
        super(LATE_LIMIT);
    }
}
