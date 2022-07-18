package meeting_room.mapper;

import meeting_room.dto.MeetingDto;
import meeting_room.entities.Meeting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		uses = {UserMapper.class, RoomMapper.class})
public interface MeetingMapper {
	@Mapping(target = "room", source = "roomDto")
	@Mapping(target = "ownerID", source = "userDto")
	Meeting toMeeting(MeetingDto meetingDto) ;
}
