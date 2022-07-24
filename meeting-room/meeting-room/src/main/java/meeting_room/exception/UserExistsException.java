package meeting_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class UserExistsException extends RuntimeException{
	private static final String USER_WITH_THIS_NUMBER_EXISTS = "Пользователь с таким номером телефона существует!";
	public UserExistsException() {
		super(USER_WITH_THIS_NUMBER_EXISTS);
	}
}
