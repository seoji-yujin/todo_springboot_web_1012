package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class Todo {
    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Integer id;
    private String content;
    private Date date;
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
