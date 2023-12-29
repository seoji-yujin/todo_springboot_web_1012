package project.maybedo.member.memberDTO;

import lombok.Data;

@Data
public class MemberJoinDTO {
    String email;
    String name;
    String username;
    String password;
    String photo_url;
}
