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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone")
    private String phone;

    @Column(name = "position")
    private String position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(cascade={CascadeType.MERGE})
    @JoinTable(name = "usersInMeeting", joinColumns = {@JoinColumn(name="userId", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="id", referencedColumnName="id")})
    private List<Meeting> meetingList;

    @OneToMany(fetch = FetchType.LAZY)
    List<Meeting> meetings;
}
