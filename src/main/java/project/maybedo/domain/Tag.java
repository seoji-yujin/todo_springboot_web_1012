package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Tag {
    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;
    private String content;

    @OneToMany(mappedBy = "tag")
    private List<GroupTag> group_tag_list = new ArrayList<>();
}
