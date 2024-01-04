package project.maybedo.streak;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import project.maybedo.member.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Streak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "streak_id")
    private int id;

    private LocalDate date;

    private double percent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "member_id")
    private Member member;
}
