package meeting_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class MinTimeIntervalException extends RuntimeException{
	private static final String MIN_TIME_INTERVAL = "Мнимальный период бронирования должен быть 30 минут!";
	public MinTimeIntervalException() {
		super(MIN_TIME_INTERVAL);
	}
}
