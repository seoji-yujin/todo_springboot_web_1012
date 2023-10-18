package project.maybedo.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GroupDto {
    private String name;
    private Long limit_member;
    private String description;
    private String photo_url;
}
