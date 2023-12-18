package project.maybedo.group.groupJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/group/join/{group_id}")
    public ResponseDto<String> joinGroup(@PathVariable int group_id, HttpSession session)
    {
        Member member = (Member)session.getAttribute("principal");
        if (groupService.joinGroup(group_id, member) == -1)
            return new ResponseDto<String>(HttpStatus.OK.value(), "Already Join");
        if (groupService.joinGroup(group_id, member) == -2)
            return new ResponseDto<String>(HttpStatus.OK.value(), "Over Limit");
        return new ResponseDto<String>(HttpStatus.OK.value(), "Success");
    }
}
