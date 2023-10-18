package project.maybedo.group.join;

import lombok.Getter;
import lombok.Setter;
import project.maybedo.group.domain.Group;
import project.maybedo.member.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Join {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "join_id")
<<<<<<< HEAD:src/main/java/project/maybedo/domain/Join.java
    private Integer id;

    private LocalDateTime date;
    private Boolean Leader;
=======
    private int id;
    private LocalDate date;
>>>>>>> upstream/master:src/main/java/project/maybedo/group/join/Join.java
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

}
