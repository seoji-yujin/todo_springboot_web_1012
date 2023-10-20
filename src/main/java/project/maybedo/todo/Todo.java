package project.maybedo.todo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import project.maybedo.member.Member;
import project.maybedo.domain.Select;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Todo {
    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private int id;
    private String content;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "todo")
    private List<Select> todo_group_list = new ArrayList<>();
}
