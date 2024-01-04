package project.maybedo.streak;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.maybedo.dto.ResponseDto;
import project.maybedo.group.domain.Group;
import project.maybedo.member.Member;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StreakController {

    private final StreakService streakService;

    // 오늘 달성률 조회
    @GetMapping("/streak")
    public ResponseDto<Streak> todayStreak (HttpSession session)
    {
        LocalDate today = LocalDate.now();
        Member member = (Member) session.getAttribute("principal");

        Streak streak = streakService.getTodayStreak(today, member);
        return new ResponseDto<Streak> (HttpStatus.OK.value(), streak);
    }


    // 모든 날짜 달성률 조회
    @GetMapping("/streaks")
    public ResponseDto<List<Streak>> allStreaks (HttpSession session)
    {
        Member member = (Member) session.getAttribute("principal");

        List<Streak> streaks = streakService.getAllStreaks(member);
        return new ResponseDto<List<Streak>> (HttpStatus.OK.value(), streaks);
    }
}
