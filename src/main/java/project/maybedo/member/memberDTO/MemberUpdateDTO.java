package project.maybedo.member.memberDTO;

import lombok.Data;

@Data
public class MemberUpdateDTO {
    String name;
    String email;
    String message;  // 한줄소개
    String photo_url;
}

