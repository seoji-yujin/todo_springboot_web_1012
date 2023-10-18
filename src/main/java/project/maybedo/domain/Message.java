package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;
import project.maybedo.group.Group;
import project.maybedo.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Message {
    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;
    private String content;
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

