package meeting_room.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "end")
    private LocalDate end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "roomId", unique = true, nullable = false)
    private Room room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(cascade={CascadeType.MERGE})
    @JoinTable(name = "usersInMeeting", joinColumns = {@JoinColumn(name="meetingId", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="id", referencedColumnName="id")})
    private List<User> userList;
}
