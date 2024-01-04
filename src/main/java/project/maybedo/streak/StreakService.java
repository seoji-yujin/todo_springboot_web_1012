package project.maybedo.streak;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.maybedo.member.Member;
import project.maybedo.member.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StreakService {

    private final StreakRepository streakRepository;
    private final MemberRepository memberRepository;

    public Streak getTodayStreak(LocalDate today, Member member)
    {
        Streak streak = streakRepository.findByMemberAndDate(member, today);
        return (streak);
    }

    public List<Streak> getAllStreaks(Member member)
    {
        List<Streak> streaks = streakRepository.findByMember(member);
        return (streaks);
    }

    // 모든 멤버 매일 밤 12시에 새로운 스트릭 생성
    @Scheduled(cron = "0 0 0 * * ?")
    public void setTodayStreak() {
        List<Member> members = memberRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Member member : members) {

            Streak streak = new Streak();
            streak.setDate(today);
            streak.setPercent(0);
            streak.setMember(member);

            streakRepository.save(streak);
        }
    }
}
