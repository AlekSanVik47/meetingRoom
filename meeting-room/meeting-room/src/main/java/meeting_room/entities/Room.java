package meeting_room.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "roomNumber")
    private int roomNumber;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "television")
    private String television;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "meetingId")
//    private List<Meeting> meetingList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
