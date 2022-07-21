package meeting_room.repositories;

import meeting_room.entities.Meeting;
import meeting_room.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    Meeting findMeetingById(Long meetingId);
    @Query("select m from Meeting m where m.startMeet < ?1")
    List<Meeting> weeklyMeetingList(ZonedDateTime startMeet);

    @Query("select m from Meeting m where m.ownerID.id = ?1")
    List<Meeting> getUserMeetings(Long id);

    @Query("select m from Meeting m where m.userList in ?1")
    List<Meeting> listOfParticipants(Collection<User> userLists);
}