package project.maybedo.todo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoCreateDTO {
    private String content;
    private LocalDate date;
}
