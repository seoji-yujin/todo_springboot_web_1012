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

    // 해시태그 리스트
//    private List<int> tagIds;
}
