package project.maybedo.member.memberDTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberJoinDTO {
    String email;
    String name;
    String username;
    String password;
    MultipartFile image_file;
}
