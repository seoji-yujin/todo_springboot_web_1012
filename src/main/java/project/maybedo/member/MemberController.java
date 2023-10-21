package project.maybedo.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.dto.ResponseDto;
import project.maybedo.member.memberDTO.MemberJoinDTO;
import project.maybedo.member.memberDTO.MemberLoginDTO;
import project.maybedo.member.memberDTO.MemberUpdateDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/member/join")
    public ResponseDto<Integer> save(@RequestBody MemberJoinDTO memberJoinDTO) {
        System.out.println("join 호출됨");
        int id = memberService.join(memberJoinDTO);
        if (id == -1)   // 이미 존재하는 회원
            return new ResponseDto<Integer>(HttpStatus.OK.value(), id);
        return new ResponseDto<Integer> (HttpStatus.OK.value(), id);
    }

    // 로그인
    @PostMapping("/member/login")
    public ResponseDto<Integer> login(@RequestBody MemberLoginDTO memberLoginDTO, HttpSession session) {
        System.out.println("login 호출됨");
        Member principal = memberService.login(memberLoginDTO.getUsername(), memberLoginDTO.getPassword());

        if (principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer> (HttpStatus.OK.value(), 1);
    }

    // 회원 프로필 수정
    @PutMapping("/member/update")
    public ResponseDto<Integer> update(@RequestBody MemberUpdateDTO memberUpdateDTO, HttpSession session) {
        Member member = (Member) session.getAttribute("principal");
        memberService.update(memberUpdateDTO, member);
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
