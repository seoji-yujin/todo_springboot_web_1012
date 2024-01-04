package project.maybedo.streak;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.member.Member;

import java.time.LocalDate;
import java.util.List;

public interface StreakRepository extends JpaRepository<Streak, Integer> {

    Streak findByMemberAndDate(Member member, LocalDate date);

    List<Streak> findByMember(Member member);
}
