package project.maybedo.todo;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import project.maybedo.member.Member;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService
{
    private final TodoRepository todoRepository;

    // 투두 작성
    public Todo create(Member member, String content, LocalDate date) {
        Todo todo = new Todo();
        todo.setMember(member);
        todo.setContent(content);
        todo.setStatus(Status.YET);
        if (date == null)
            todo.setDate(LocalDate.now());
        else
            todo.setDate(date);  // 날짜 정보가 들어온다면 해당 날짜에 투두 저장

        this.todoRepository.save(todo);
        return (todo);
    }

    // 완료 표시
    public void done(int id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        todo.setStatus(Status.DONE);
        this.todoRepository.save(todo);
    }

    // 투두 삭제
    public void delete(int id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        this.todoRepository.delete(todo);
    }

    // 투두 수정
    public Todo update(int id, String content) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        todo.setContent(content);
        this.todoRepository.save(todo);
        return (todo);
    }

    // 날짜와 멤버로 투두 조회
    public List<Todo> getTodosByMemberAndDate(Member member, LocalDate today){
        return todoRepository.findByMemberAndDate(member, today);
    }
}
