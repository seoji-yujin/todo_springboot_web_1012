package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;
import project.maybedo.group.domain.Group;
import project.maybedo.todo.Todo;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Select {
    @Id
    @GeneratedValue
    @Column(name = "todo_group_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="todo_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group group;
}
