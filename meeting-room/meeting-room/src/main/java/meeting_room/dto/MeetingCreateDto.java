package meeting_room.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class MeetingCreateDto implements Serializable {
    private final ZonedDateTime start;
    private final ZonedDateTime end;
    private final Long roomId;
    private final Long ownerId;
}
