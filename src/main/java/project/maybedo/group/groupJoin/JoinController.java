package project.maybedo.group.groupJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.maybedo.dto.ResponseDto;
import project.maybedo.member.Member;
import project.maybedo.group.GroupService;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final GroupService groupService;

    // 그룹 가입
    @PostMapping("/group/join/{group_id}")
    public ResponseDto<String> joinGroup(@PathVariable int group_id, HttpSession session)
    {
        int error_flag;
        Member member = (Member)session.getAttribute("principal");

        error_flag = groupService.joinGroup(group_id, member);
        if (error_flag == -1)
            return new ResponseDto<String>(HttpStatus.OK.value(), "Already Join");
        else if (error_flag == -2)
            return new ResponseDto<String>(HttpStatus.OK.value(), "Over Limit");
        return new ResponseDto<String>(HttpStatus.OK.value(), "Success");
    }

    // 그룹 나가기
    @DeleteMapping("/group/{group_id}")
    public ResponseDto<Integer> outGroup(@PathVariable int group_id, HttpSession session)
    {
        Member member = (Member)session.getAttribute("principal");
        groupService.removeUserFromGroup(member.getId(), group_id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);}
}
