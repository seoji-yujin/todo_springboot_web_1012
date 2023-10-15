package project.maybedo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ResponseDto<Integer> createGroup(@RequestBody Group group, HttpSession session)
    {
        Member member = (Member)session.getAttribute("principal");
        groupService.create(group, member);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/group/delete/{id}")
    public ResponseDto<Integer> deleteGroup(@PathVariable Integer id, HttpSession session)
    {
        Member member = (Member) session.getAttribute("principal");
        groupService.delete(id, member.getId());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
