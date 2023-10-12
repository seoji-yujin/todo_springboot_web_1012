package project.maybedo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.domain.Member;
import project.maybedo.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 멤버 생성
    @PostMapping("/member/new")
    public String memberCreate(@RequestBody Member member) {  // @RequestBody가 알아서 member에 맞춰 넣어줌
        memberService.join(member);
        return ("member id" + member.getId());
    }

    // 로그인
    @GetMapping("/member/login")
    public String login() {
        return ("login");
    }

    // 멤버 1명 조회
    @GetMapping("/member")
    public Member viewMember(@RequestParam int id) {
        Member member = memberService.getMember(id);
        return (member);
    }

    // 멤버 리스트로 조회
    @GetMapping("/members")
    public List<Member> list(Model model) {
        List<Member> memberList = memberService.getList();
        model.addAttribute("memberlist", memberList);
        return (memberList);
    }
}
