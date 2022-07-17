package meeting_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class PeriodCannotBeUsedException extends RuntimeException {
	private static final String PERIOD_ALREADY_IN_USE = "Период уже используется!";
	public PeriodCannotBeUsedException() {
		super(PERIOD_ALREADY_IN_USE);
	}
}
