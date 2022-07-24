package meeting_room.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(PeriodCannotBeUsedException.class)
    public ResponseEntity<ErrorMessage> periodCannotBeUsedException(PeriodCannotBeUsedException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(ExceedsCapacityException.class)
    public ResponseEntity<ErrorMessage> exceedsCapacityException(ExceedsCapacityException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(exception.getMessage()));
    }
    @ExceptionHandler(MinTimeIntervalException.class)
    public ResponseEntity<ErrorMessage> minTimeIntervalException(MinTimeIntervalException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(exception.getMessage()));
    }
  @ExceptionHandler(LateLimitException.class)
    public ResponseEntity<ErrorMessage> lateLimitException(LateLimitException exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(exception.getMessage()));
  }
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorMessage> userExistsException (UserExistsException exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(UserHasMeetingsException.class)
    public ResponseEntity<ErrorMessage> userHasMeetingsException (UserHasMeetingsException exception){return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorMessage(exception.getMessage()));
    }

}
