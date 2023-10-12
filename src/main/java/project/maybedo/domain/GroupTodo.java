package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class GroupTodo {
    @Id
    @GeneratedValue
    @Column(name = "group_todo_id")
    private Long id;
    private String content;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
}

