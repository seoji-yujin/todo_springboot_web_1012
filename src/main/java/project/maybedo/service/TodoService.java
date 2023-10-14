package project.maybedo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.domain.Member;
import project.maybedo.domain.Status;
import project.maybedo.domain.Todo;
import project.maybedo.repository.TodoRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService
{
    private final TodoRepository todoRepository;

    /**
     * 투두 작성
     */
    public void create(Member member, String content) {
        Todo todo = new Todo();
        todo.setMember(member);
        todo.setContent(content);
        todo.setStatus(Status.YET);
        todo.setDate(new Date());

        this.todoRepository.save(todo);
    }

    /**
     * 완료 표시
     */
    public void done(Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        todo.setStatus(Status.DONE);

        this.todoRepository.save(todo);
    }

    /**
     * 투두 삭제
     */
    public void delete(Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        this.todoRepository.delete(todo);
    }

    /**
     * 투두 수정
     */
    public void update(Integer id, String content) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        todo.setContent(content);

        this.todoRepository.save(todo);
    }

    /**
     * 투두 리스트로 조회
     */
    public List<Todo> getTodoList() {
        return this.todoRepository.findAll();
    }

}
