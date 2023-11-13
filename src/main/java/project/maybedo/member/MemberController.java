package project.maybedo.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.dto.ResponseDto;
import project.maybedo.group.domain.Group;
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

        if (principal != null)
            session.setAttribute("principal", principal);
        if (principal == null)
            return new ResponseDto<Integer> (HttpStatus.OK.value(), -1);
        return new ResponseDto<Integer> (HttpStatus.OK.value(), 1);
    }

    // 회원 프로필 수정
    @PutMapping("/member/update")
    public ResponseDto<Member> update(@RequestBody MemberUpdateDTO memberUpdateDTO, HttpSession session) {
        Member member = (Member) session.getAttribute("principal");
        Member update_member = memberService.update(memberUpdateDTO, member);
        return new ResponseDto<Member> (HttpStatus.OK.value(), update_member);
    }

    // 멤버 1명 조회
    @GetMapping("/member")
    public ResponseDto<Member> viewMember(@RequestParam int id) {
        Member member = memberService.getMember(id);
        return new ResponseDto<Member> (HttpStatus.OK.value(), member);
    }

    // 멤버 리스트로 조회
    @GetMapping("/members")
    public ResponseDto<List<Member>> list(Model model) {
        List<Member> memberList = memberService.getList();
        model.addAttribute("memberlist", memberList);
        return new ResponseDto<List<Member>> (HttpStatus.OK.value(), memberList);
    }

    // 내가 가입한 그룹 리스트 가져오기
    @GetMapping("/member/my_group")
    public ResponseDto<List<Group>> myGroupList(HttpSession session)
    {
        Member member = (Member) session.getAttribute("principal");
        List<Group> myGroups = memberService.getMyGroups(member);
        for (Group g : myGroups) {
            System.out.println("group: "+ g.getId());
        }
//        return new ResponseDto<List<Group>> (HttpStatus.OK.value(), myGroups);
        return ResponseDto.<List<Group>>builder()
                .status(HttpStatus.OK.value())
                .data(myGroups)
                .build();
    }


}
