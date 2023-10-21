package project.maybedo.group;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.maybedo.dto.ResponseDto;
import project.maybedo.group.domain.Group;
import project.maybedo.member.Member;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    // 그룹 생성
    @PostMapping("/group/create")
    public ResponseDto<Integer> createGroup(@RequestBody Group group, HttpSession session)
    {
        Member member = (Member)session.getAttribute("principal");
        groupService.create(group, member);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 그룹 삭제
    @DeleteMapping("/group/delete/{id}")
    public ResponseDto<Integer> deleteGroup(@PathVariable int id, HttpSession session)
    {
        Member member = (Member) session.getAttribute("principal");
        groupService.delete(id, member.getId());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 그룹의 멤버를 반환하는 컨트롤러
    @GetMapping ("/group/members/{id}")
    public ResponseDto<Integer> findMemberInGroup(@PathVariable int id)
    {
        List<Member> members = groupService.findMemberInGroup(id);
        for (Member m : members) {
            System.out.println("member: "+m.getUsername());
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}