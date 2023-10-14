package project.maybedo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.domain.Member;
import project.maybedo.dto.LoginFormDto;
import project.maybedo.dto.MemberFormDto;
import project.maybedo.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // 멤버 생성(회원 가입)
    @PostMapping("/member/join")
    public String memberCreate(@RequestBody MemberFormDto memberFormDto) {
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberService.save(member);
        return ("회원가입 완료 : member id" + member.getId());
    }

    // 로그인
    @PostMapping("/member/login")
    public String login(@RequestBody LoginFormDto loginFormDto) {
        memberService.loadUserByUsername(loginFormDto.getEmail());
        return ("로그인 완료");
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
