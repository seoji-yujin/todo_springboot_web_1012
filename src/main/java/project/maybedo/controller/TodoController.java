package project.maybedo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.domain.Todo;
import project.maybedo.service.TodoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // http://localhost:8080/todo
//    @RequestMapping("/todo")
//    public ResponseEntity<Map<String, Objects>> list2(Model model) {
//        List<Todo> todoList = todoService.getTodoList();
//        model.addAttribute("todoList", todoList);
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("status", 200);
//        result.put("data", todoList);
//
//        return (new ResponseEntity.of(result));
//    }
    @RequestMapping("/todo")
    public List<Todo> list(Model model) {
        List<Todo> todoList = todoService.getTodoList();
        model.addAttribute("todoList", todoList);
        return (todoList);
    }

   // 투두 작성
    @PostMapping("/todo/create")
    public String todoCreate(@RequestParam String content) {
        todoService.create(content);
        return ("todo : " + content);
    }

    // 투두 완료 체크
    @PutMapping("/todo/done/{id}")
    public String todoDoneCheck(@PathVariable Integer id) {
        todoService.done(id);
        return ("todo :  " + id);
    }

    // 투두 삭제
    @DeleteMapping("/todo/delete/{id}")
    public String todoDelete(@PathVariable Integer id) {
        todoService.delete(id);
        return ("todo : " + id);
    }

    // 투두 수정
    @PutMapping("/todo/update/{id}")
    public String todoUpdate(@PathVariable Integer id, @RequestParam String content) {
        todoService.update(id, content);
        return ("todo : " + content);
    }

}
