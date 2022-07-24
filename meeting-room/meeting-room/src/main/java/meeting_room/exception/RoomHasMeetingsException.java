package meeting_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class RoomHasMeetingsException extends RuntimeException{
	private static final String ROOM_HAS_MEETINGS = "В комнате запланированы встречи!";
	public RoomHasMeetingsException(){super(ROOM_HAS_MEETINGS);}
}
