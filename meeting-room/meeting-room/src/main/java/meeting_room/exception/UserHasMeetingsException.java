package meeting_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class UserHasMeetingsException extends RuntimeException {
	private static final String USER_HAS_MEETING = "У пользователя есть встречи!";
	public UserHasMeetingsException() {
		super(USER_HAS_MEETING);
	}
}
