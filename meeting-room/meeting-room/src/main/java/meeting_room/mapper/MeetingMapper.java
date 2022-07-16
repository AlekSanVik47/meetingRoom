package meeting_room.mapper;

import meeting_room.dto.MeetingDto;
import meeting_room.entities.Meeting;
import meeting_room.entities.Room;
import meeting_room.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {RoomMapper.class})
public interface MeetingMapper {
	@Mapping(target = "room", source = "id")
	Meeting toMeeting(MeetingDto meetingDto);
}
