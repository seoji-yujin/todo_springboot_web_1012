package project.maybedo.streak;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.maybedo.dto.ResponseDto;
import project.maybedo.group.domain.Group;
import project.maybedo.member.Member;
import project.maybedo.member.MemberRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StreakController {

    private final StreakService streakService;
    private final MemberRepository memberRepository;

    // 특정 사용자의 오늘 달성률 조회
    @GetMapping("/streak/{username}")
    public ResponseDto<Streak> todayStreak (@PathVariable String username)
    {
        LocalDate today = LocalDate.now();
        Member member = memberRepository.findByUsername(username);

        Streak streak = streakService.getTodayStreak(today, member);
        return new ResponseDto<Streak> (HttpStatus.OK.value(), streak);
    }


    // 특정 사용자의 모든 날짜 달성률 조회
    @GetMapping("/streaks/{username}")
    public ResponseDto<List<Streak>> allStreaks (@PathVariable String username)
    {
        Member member = memberRepository.findByUsername(username);

        List<Streak> streaks = streakService.getAllStreaks(member);
        return new ResponseDto<List<Streak>> (HttpStatus.OK.value(), streaks);
    }
}
