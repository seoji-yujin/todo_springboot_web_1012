package project.maybedo.group.groupTag;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import project.maybedo.group.domain.Group;
import project.maybedo.tag.Tag;

import javax.persistence.*;

@Entity
@Getter @Setter
public class GroupTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_tag_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="group_id")
    @JsonBackReference
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id")
    @JsonManagedReference
    private Tag tag;
}
