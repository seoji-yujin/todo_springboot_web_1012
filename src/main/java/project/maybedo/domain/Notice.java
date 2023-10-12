package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Notice {
        @Id
        @GeneratedValue
        @Column(name = "notice_id")
        private Long id;
        private String content;
        private LocalDateTime date;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "group_id")
        private Group group;
    }