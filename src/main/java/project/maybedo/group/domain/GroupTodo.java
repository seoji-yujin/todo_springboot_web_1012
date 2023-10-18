package project.maybedo.group.domain;

import lombok.Getter;
import lombok.Setter;
import project.maybedo.todo.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class GroupTodo {
    @Id
    @GeneratedValue
    @Column(name = "group_todo_id")
    private int id;
    private String content;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
}

