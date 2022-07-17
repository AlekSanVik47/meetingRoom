package meeting_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ExceedsCapacityException extends RuntimeException {
   private static final String CAPACITY_DOES_NOT_ALLOW = "Количество свободных мест заполнено!";
	public ExceedsCapacityException() {
		super(CAPACITY_DOES_NOT_ALLOW);
	}
}
