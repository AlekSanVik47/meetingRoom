package meeting_room.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String login;
    private final String password;
    private final String name;
    private final String surname;
    private final String patronymic;
    private final String phone;
    private final String position;
}
