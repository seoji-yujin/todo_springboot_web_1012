package project.maybedo.streak;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.member.Member;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StreakService {

    private final StreakRepository streakRepository;

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
}
