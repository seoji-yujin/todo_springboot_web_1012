package project.maybedo.repository;

import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.domain.Member;
import project.maybedo.domain.Todo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    // 날짜로 투두 리스트 가져오기를 findall()로 빼기
    List<Todo> findByMemberAndDate(Member member, LocalDate today);
}
