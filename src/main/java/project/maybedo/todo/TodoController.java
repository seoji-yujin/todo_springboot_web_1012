package project.maybedo.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.dto.ResponseDto;
import project.maybedo.member.Member;
import project.maybedo.todo.dto.TodoCreateDTO;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 투두 메인 화면 (투두 리스트 조회)
    @RequestMapping("/todo")
    public ResponseDto<Integer> list(Model model) {
        List<Todo> todoList = todoService.getTodoList();
        model.addAttribute("todoList", todoList);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 오늘의 투두 리스트 가져오기
    @GetMapping("/today/todo")
    public ResponseDto<Integer> getTodosForToday(Model model, HttpSession session) {

        Member member = (Member) session.getAttribute("principal");
        /**
         * 사용자 없을 경우 예외 코드 추가하기
         */
        LocalDate today = LocalDate.now();
        List<Todo> todos = todoService.getTodosByMemberAndDate(member, today);
        model.addAttribute("todos", todos);

        // 투두 목록을 로그에 출력
        for (Todo todo : todos) {
            System.out.println("Todo: " + todo.getContent());
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 투두 생성
    @PostMapping("/todo/create")
    public ResponseDto<Integer> createTodo(@RequestBody TodoCreateDTO todoCreateDTO, HttpSession session) {
        // 세션에서 사용자 정보를 가져옴
        Member member = (Member)session.getAttribute("principal");
        todoService.create(member, todoCreateDTO.getContent(), todoCreateDTO.getDate());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 투두 완료 체크
    @PutMapping("/todo/done/{id}")
    public ResponseDto<Integer> todoDoneCheck(@PathVariable int id) {
        todoService.done(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 투두 삭제
    @DeleteMapping("/todo/delete/{id}")
    public ResponseDto<Integer> todoDelete(@PathVariable int id) {
        todoService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 투두 수정
    @PutMapping("/todo/update/{id}")
    public ResponseDto<Integer> todoUpdate(@PathVariable int id, @RequestParam String content) {
        todoService.update(id, content);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
