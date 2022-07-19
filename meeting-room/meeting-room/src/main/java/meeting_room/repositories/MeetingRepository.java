package meeting_room.repositories;

import meeting_room.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {


    Meeting findMeetingById(Long meetingId);

}