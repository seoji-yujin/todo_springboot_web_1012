package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Group {
    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private int id;
    private String name;
    private LocalDateTime expire_date;
    private Long limit_member;
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
    private List<Join> join_list = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<GroupTag> group_tag_list = new ArrayList<>();
}
