package project.maybedo.member.memberDTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberJoinDTO {
    String email;
    String name;
    String username;
    String password;
    MultipartFile image_file;
}
