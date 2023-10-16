package project.maybedo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.maybedo.controller.dto.GroupDto;
import project.maybedo.controller.dto.ResponseDto;
import project.maybedo.domain.Group;
import project.maybedo.domain.Member;
import project.maybedo.service.GroupService;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    @PostMapping("/group/create")
    public ResponseDto<Integer> createGroup(@RequestBody GroupDto groupDto, HttpSession httpSession)
    {
        Member member = (Member)httpSession.getAttribute("principal");
        groupService.create(member, groupDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
