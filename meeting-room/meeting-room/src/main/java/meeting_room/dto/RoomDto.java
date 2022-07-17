package meeting_room.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспортный объект комнаты")
public class RoomDto implements Serializable {
    private Long id;
    private int roomNumber;
    private int capacity;
    private String television;
}
