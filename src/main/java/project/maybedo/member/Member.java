package project.maybedo.member;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import project.maybedo.maybedo.Maybedo;
import project.maybedo.domain.Message;
import project.maybedo.domain.Schedule;
import project.maybedo.group.join.Join;
import project.maybedo.todo.Todo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int id;
    private String username;
    private String email;
    private String password;
    private String message;
    private String photo_url;
    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<Todo> todo_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Maybedo> maybedo_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Schedule> schedule_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Message> message_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Join> join_list = new ArrayList<>();
}
