package project.maybedo.tag;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import project.maybedo.group.groupTag.GroupTag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Tag {
    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private int id;
    private String content;

    @OneToMany(mappedBy = "tag")
    @JsonBackReference
    private List<GroupTag> group_tag_list = new ArrayList<>();
}
