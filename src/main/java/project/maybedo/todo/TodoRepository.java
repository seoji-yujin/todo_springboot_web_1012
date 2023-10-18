package project.maybedo.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.member.Member;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    // 날짜로 투두 리스트 가져오기를 findall()로 빼기
    List<Todo> findByMemberAndDate(Member member, LocalDate today);
}
