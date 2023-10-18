package project.maybedo.todo;

import lombok.Getter;
import lombok.Setter;
import project.maybedo.member.Member;
import project.maybedo.domain.Select;
import project.maybedo.domain.Status;

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
    private Integer id;
    private String content;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "todo")
    private List<Select> todo_group_list = new ArrayList<>();

//    public TodoDto toDto() {
//        return TodoDto.builder()
//                .status(this.status)
//                .data(this.data).build();
//    }
}
