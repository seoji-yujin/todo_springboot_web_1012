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

    // 전체 그룹 조회
    @GetMapping("/groups")
    public ResponseDto<List> allList() {
        return new ResponseDto<List>(HttpStatus.OK.value(), groupService.getAll());
    }

    // 그룹 생성
    @PostMapping("/group/create")
    public ResponseDto<Group> createGroup(@RequestBody GroupCreateDTO groupCreateDTO, HttpSession session)
    {
        Member member = (Member)session.getAttribute("principal");
        Group group = groupService.create(groupCreateDTO, member);
        return new ResponseDto<Group>(HttpStatus.OK.value(), group);
    }

    // 그룹 삭제
    @DeleteMapping("/group/delete/{id}")
    public ResponseDto<Integer> deleteGroup(@PathVariable int id, HttpSession session)
    {
        Member member = (Member) session.getAttribute("principal");
        groupService.delete(id, member.getId());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), id);
    }

    // 그룹의 멤버를 반환하는 컨트롤러
    @GetMapping ("/group/members/{id}")
    public ResponseDto<Integer> findMemberInGroup(@PathVariable int id)
    {
        List<Member> members = groupService.findMemberInGroup(id);
        for (Member m : members) {
            System.out.println("member: "+m);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
