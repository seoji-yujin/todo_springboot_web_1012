package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String message;
    private String photo_url;
    @OneToMany(mappedBy = "member")
    private List<Todo> todo_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Maybedo> maybedo_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Schedule> schedule_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Message> message_list = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Join> join_list = new ArrayList<>();
}
