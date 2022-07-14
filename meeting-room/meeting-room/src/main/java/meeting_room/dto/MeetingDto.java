package meeting_room.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class MeetingDto implements Serializable {
    private final Long id;
    private final LocalDate start;
    private final LocalDate end;
    private final RoomDto room;
}
