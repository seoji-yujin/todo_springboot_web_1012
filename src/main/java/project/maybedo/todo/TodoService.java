package project.maybedo.todo;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import project.maybedo.group.groupJoin.Join;
import project.maybedo.member.Member;
import project.maybedo.member.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService
{
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    // 달성률 체크
    public void checkAchievement(Member member, LocalDate todoDate)
    {
        // 만약 수정, 추가, 삭제한 투두가 오늘 날짜라면 달성률 갱신
        LocalDate today = LocalDate.now();
        if (todoDate.equals(today))
        {
            List<Todo> todos = todoRepository.findByMemberAndDate(member, today);

            int size = todos.size();
            int success = 0;

            for(Todo todo : todos)
            {
                if (todo.getStatus() == Status.DONE)
                    success++;
            }
            double achievementPercentage = (double) success / size * 100;
            member.setAchievement(achievementPercentage);
            memberRepository.save(member);
        }
    }

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
        checkAchievement(member, todo.getDate());
        return (todo);
    }

    // 완료 표시
    public void done(int id, Member member) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id : " + id));
        if (todo.getStatus() == Status.YET)
            todo.setStatus(Status.DONE);
        else if (todo.getStatus() == Status.DONE)
            todo.setStatus(Status.YET);
        this.todoRepository.save(todo);
        checkAchievement(member, todo.getDate());
    }

    // 투두 삭제
    public void delete(int id, Member member) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        this.todoRepository.delete(todo);
        checkAchievement(member, todo.getDate());
    }

    // 투두 수정
    public Todo update(int id, Member member, String content) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        todo.setContent(content);
        this.todoRepository.save(todo);
        return (todo);
    }

    // 날짜와 멤버로 투두 조회
    public List<Todo> getTodosByMemberAndDate(Member member, LocalDate date){
        return todoRepository.findByMemberAndDate(member, date);
    }


}
