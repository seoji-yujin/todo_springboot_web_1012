package project.maybedo.group.groupNotice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import project.maybedo.group.domain.Group;

import javax.persistence.*;

@Entity
@Getter @Setter
public class GroupNotice {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "group_notice_id")
        private int id;
        private String content;
        @ManyToOne(fetch = FetchType.LAZY)
        @JsonBackReference
        @JoinColumn(name = "group_id")
        private Group group;
    }