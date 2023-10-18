package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;
import project.maybedo.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Schedule {
    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;
    private String content;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
