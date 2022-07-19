package meeting_room.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспортный объект встречи")
public class MeetingCreateDto implements Serializable {
    private ZonedDateTime start;
    private ZonedDateTime end;
    private Long roomId;
    private Long ownerIDId;
}
