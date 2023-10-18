package project.maybedo.maybedo;

import lombok.Getter;
import lombok.Setter;
import project.maybedo.member.Member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter @Setter
public class Maybedo {
    @Id
    @GeneratedValue
    @Column(name = "maybedo_id")
    private int id;
    private String content;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
