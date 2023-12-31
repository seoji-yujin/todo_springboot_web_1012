package project.maybedo.group;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SqlFragmentAlias;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.maybedo.dto.ResponseDto;
import project.maybedo.group.domain.Group;
import project.maybedo.member.Member;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    // 전체 그룹 조회
    @GetMapping("/groups")
    public ResponseDto<List> allList() {
        return new ResponseDto<List>(HttpStatus.OK.value(), groupService.getAll());
    }

    // 그룹 하나 조회
    @GetMapping("/group/{id}")
    public ResponseDto<Optional<Group>> getGroup(@PathVariable int id) {
        return new ResponseDto<Optional<Group>>(HttpStatus.OK.value(),  groupService.getGroup(id));
    }

    // 그룹 가입 여부 반환
    @GetMapping("/group/check_join/{group_id}")
    public ResponseDto<String> getGroup(@PathVariable int group_id, HttpSession session) {
        Member member = (Member)session.getAttribute("principal");
        int member_id = member.getId();
        if (groupService.isJoin(group_id, member_id))
            return new ResponseDto<String>(HttpStatus.OK.value(),  "join");
        return new ResponseDto<String>(HttpStatus.OK.value(),  "not join");

    }

    // 그룹 생성
    @PostMapping("/group/create")
    public ResponseDto<Group> createGroup(GroupCreateDTO groupCreateDTO, HttpSession session)
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
    public ResponseDto<List<Member>> findMemberInGroup(@PathVariable int id)
    {
        List<Member> members = groupService.findMemberInGroup(id);
        for (Member m : members) {
            System.out.println("member: "+m);
        }
        return new ResponseDto<List<Member>>(HttpStatus.OK.value(), members);
    }

}
