package meeting_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class UserNotFoundException extends RuntimeException {
	private static final String USER_IS_NOT_FOUND = "Пользователь не найден";

	public UserNotFoundException() {
		super(USER_IS_NOT_FOUND);
	}


}
