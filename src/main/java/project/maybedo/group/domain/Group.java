package project.maybedo.group.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import project.maybedo.domain.*;
import project.maybedo.group.groupJoin.Join;
import project.maybedo.group.groupTag.GroupTag;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Group {
    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private int id;
    private Integer leader;  // 그룹장의 아이디
    private String name;
    private LocalDate expire_date;
    private Integer limit_member;
    private String description;
    private String photo_url;

    @OneToMany(mappedBy = "group")
    private List<GroupTodo> group_todo_list = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Notice> notice_list = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Message> message_list = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Select> select_list = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    private List<Join> join_list = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    private List<GroupTag> groupTags = new ArrayList<>();
}
