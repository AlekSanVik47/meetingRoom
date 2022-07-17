package meeting_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class DataNotInDBException extends RuntimeException{
	private static final String INTERNAL_SERVER_ERROR = "Данные не найдены в БД";
	public DataNotInDBException() {
		super(INTERNAL_SERVER_ERROR);
	}
}
