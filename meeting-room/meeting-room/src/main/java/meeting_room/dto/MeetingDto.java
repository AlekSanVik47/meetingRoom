package meeting_room.dto;

import lombok.Data;
import meeting_room.entities.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class MeetingDto implements Serializable {
    private final Long id;
    private final LocalDate start;
    private final LocalDate end;
    private final RoomDto room;
    private final User user;
}
