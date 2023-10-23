package project.maybedo.group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupCreateDTO {
    private String name;
    private Integer limit_member;
    private String description;
    private String photo_url;
}
