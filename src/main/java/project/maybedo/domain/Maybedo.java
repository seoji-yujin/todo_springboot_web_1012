package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
public class Maybedo {
    @Id
    @GeneratedValue
    @Column(name = "maybedo_id")
    private Long id;
    private String content;
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
