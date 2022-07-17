package meeting_room.mapper;

import meeting_room.dto.UserDto;
import meeting_room.entities.Meeting;
import meeting_room.entities.User;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {Meeting.class})
public interface UserMapper {

    User toUser(UserDto dto);

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromUserDto(UserDto userDto, @MappingTarget User user);
}
