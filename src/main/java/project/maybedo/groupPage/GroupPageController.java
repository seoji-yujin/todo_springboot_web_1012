//package project.maybedo.groupPage;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import project.maybedo.dto.ResponseDto;
//import project.maybedo.group.GroupRepository;
//import project.maybedo.group.GroupService;
//import project.maybedo.group.domain.Group;
//import project.maybedo.group.domain.GroupTodo;
//import project.maybedo.groupPage.groupPageDTO.ResponseDTO;
//import project.maybedo.member.Member;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class GroupPageController {
//
//    private final GroupRepository groupRepository;
//    private final GroupService groupService;
//
//    // 그룹 페이지 조회
//    @GetMapping("/group/page")
//    public ResponseDto<ResponseDTO> groupFrontPage(@RequestParam int id)
//    {
//        Group group = groupRepository.findById(id)
//                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹 : " + id));
//        // 그룹 투두
//        // 그룹 공지
//        // 그룹 멤버 가져오기 -> 멤버의 이름
//        GroupTodo todayGroupTodo = GroupTodoRepository.findByDate(LocalDate.now());
//        GroupNotice notice = GroupNoticeRepository.findById(id);
//        List<Member> memberList = groupService.findMemberInGroup(id);
//
//        ResponseDTO responseDTO = new ResponseDTO();
//        responseDTO.setGroupTodo(todayGroupTodo);
//        responseDTO.setGroupNotice(notice);
//        responseDTO.setMemberList(memberList);
//        return new ResponseDto<ResponseDTO>(HttpStatus.OK.value(), responseDTO);
//    }
//}
