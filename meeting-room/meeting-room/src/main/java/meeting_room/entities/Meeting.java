package meeting_room.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meeting_room.dto.UserDto;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start")
    private ZonedDateTime start;

    @Column(name = "end")
    private ZonedDateTime end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "roomId", unique = true, nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "userId", unique = true, nullable = false)
    private User ownerID;


    @ManyToMany(cascade={CascadeType.MERGE})
    @JoinTable(name = "usersInMeeting", joinColumns = {@JoinColumn(name="meetingId", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="id", referencedColumnName="id")})
    private List<User> userList;

    public void setRoom(Room room) {
        this.room = room;
    }


}
