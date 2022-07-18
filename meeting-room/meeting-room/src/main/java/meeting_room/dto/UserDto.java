package meeting_room.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import meeting_room.entities.Meeting;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспортный объект пользователя")
public class UserDto {
    private Long id;
	private String login;
	private  String password;
	private  String name;
	private  String surname;
	private  String patronymic;
	private  String phone;
	private  String position;
}
