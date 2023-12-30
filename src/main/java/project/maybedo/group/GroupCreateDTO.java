package project.maybedo.group;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class GroupCreateDTO {
    private String name;
    private Integer limit_member;
    private String description;
    private MultipartFile image_file;

    // 해시태그 리스트
    private List<String> tag;
}
