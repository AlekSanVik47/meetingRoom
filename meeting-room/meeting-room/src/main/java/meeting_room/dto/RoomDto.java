package meeting_room.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomDto implements Serializable {
    private final Long id;
    private final int roomNumber;
    private final int capacity;
    private final String television;
}
