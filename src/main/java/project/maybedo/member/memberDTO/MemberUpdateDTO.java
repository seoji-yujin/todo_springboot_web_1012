package project.maybedo.member.memberDTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberUpdateDTO {
    String name;
    String email;
    String message;  // 한줄소개
    MultipartFile image_file;
}

