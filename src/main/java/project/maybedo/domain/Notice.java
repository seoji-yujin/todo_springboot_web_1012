package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;
import project.maybedo.group.domain.Group;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Notice {
        @Id
        @GeneratedValue
        @Column(name = "notice_id")
        private int id;
        private String content;
        private LocalDate date;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "group_id")
        private Group group;
    }