package project.maybedo.group.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import project.maybedo.tag.Tag;

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
    @JsonBackReference
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id")
    @JsonManagedReference
    private Tag tag;
}
