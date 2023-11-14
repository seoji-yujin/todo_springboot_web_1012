package project.maybedo.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.dto.ResponseDto;
import project.maybedo.member.Member;
import project.maybedo.todo.todoDTO.TodoCreateDTO;
import project.maybedo.todo.todoDTO.TodoGetDTO;
import project.maybedo.todo.todoDTO.TodoUpdateDTO;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final TodoRepository todoRepository;

    // 해당 날짜 투두 리스트 가져오기
    @GetMapping("/todo/get/{date}")
    public ResponseDto<List<Todo>> getTodosForDay(
            @PathVariable(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, HttpSession session) {
        Member member = (Member) session.getAttribute("principal");
        // 사용자가 없을 경우 에러 코드 반환
        if (date == null)
            date = LocalDate.now();  // 따로 날짜를 입력받지 않았다면 오늘 날짜
        List<Todo> todos = todoService.getTodosByMemberAndDate(member, date);
        if (todos == null)
            return new ResponseDto<List<Todo>>(HttpStatus.NOT_FOUND.value(), null);  // 빈 화면
        return new ResponseDto<List<Todo>>(HttpStatus.OK.value(), todos);
    }

    // 투두 생성
    @PostMapping("/todo/create")
    public ResponseDto<Todo> createTodo(@RequestBody TodoCreateDTO todoCreateDTO, HttpSession session) {
        // 세션에서 사용자 정보를 가져옴
        Member member = (Member)session.getAttribute("principal");
        Todo todo = todoService.create(member, todoCreateDTO.getContent(), todoCreateDTO.getDate());
        System.out.println("Todo: "+todo.getContent());
        return new ResponseDto<Todo>(HttpStatus.OK.value(), todo);
    }

    // 투두 완료 체크
    @PutMapping("/todo/done/{id}")
    public ResponseDto<Integer> todoDoneCheck(@PathVariable int id) {
        todoService.done(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), id);
    }

    // 투두 삭제
    @DeleteMapping("/todo/delete/{id}")
    public ResponseDto<Integer> todoDelete(@PathVariable int id) {
        todoService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), id);
    }

    // 투두 수정
    @PutMapping("/todo/update/{id}")
    public ResponseDto<Todo> todoUpdate(@PathVariable int id, @RequestBody TodoUpdateDTO todoUpdateDTO) {
        Todo new_todo = todoService.update(id, todoUpdateDTO.getContent());
        return new ResponseDto<Todo>(HttpStatus.OK.value(), new_todo);
    }

}
