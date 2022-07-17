package meeting_room.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import meeting_room.entities.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспортный объект встречи")
public class MeetingDto implements Serializable {
    private Long id;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private RoomDto room;
    private User user;
}
