package meeting_room.mapper;

import meeting_room.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {MeetingMapper.class})
public interface RoomMapper {

//Optional<Room> toRoom(RoomDto roomDto);

Room toRoom(Long id);


}
