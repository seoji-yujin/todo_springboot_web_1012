package project.maybedo.todo.todoDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoCreateDTO {
    private String content;
    private LocalDate date;
}
