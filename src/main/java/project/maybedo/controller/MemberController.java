package project.maybedo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.controller.dto.ResponseDto;
import project.maybedo.domain.Member;
import project.maybedo.service.MemberService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/member/join")
    public ResponseDto<Integer> save(@RequestBody Member member) {
        System.out.println("join 호출됨");
        if (memberService.join(member) == -1)
            return new ResponseDto<Integer>(HttpStatus.FORBIDDEN.value(), -1);
        return new ResponseDto<Integer> (HttpStatus.OK.value(), 1);
    }

    // 로그인
    @PostMapping("/member/login")
    public ResponseDto<Integer> login(@RequestBody Member member, HttpSession session) {
        System.out.println("login 호출됨");
        Member principal = memberService.login(member);

        if (principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer> (HttpStatus.OK.value(), 1);
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
