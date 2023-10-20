package project.maybedo.group.groupJoin;

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
    @GeneratedValue
    @Column(name = "join_id")
    private int id;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

}
