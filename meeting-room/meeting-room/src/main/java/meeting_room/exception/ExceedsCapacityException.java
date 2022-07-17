package meeting_room.exception;

public class ExceedsCapacityException extends RuntimeException{

	public ExceedsCapacityException(String message) {
		super(message);
	}
}
