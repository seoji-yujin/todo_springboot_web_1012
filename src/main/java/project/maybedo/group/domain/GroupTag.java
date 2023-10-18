package project.maybedo.group.domain;

import lombok.Getter;
import lombok.Setter;
import project.maybedo.domain.Tag;

import javax.persistence.*;

@Entity
@Getter @Setter
public class GroupTag {
    @Id
    @GeneratedValue
    @Column(name = "group_tag_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id")
    private Tag tag;
}
