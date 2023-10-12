package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Select {
    @Id
    @GeneratedValue
    @Column(name = "todo_group_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="todo_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group group;
}
