package project.maybedo.group.groupNotice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.maybedo.dto.ResponseDto;
import project.maybedo.group.domain.Group;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupNoticeController {
    private final GroupNoticeService groupNoticeService;

    @GetMapping("/group/{group_id}/notices")
    public ResponseDto<List<GroupNotice>> getNoticeList(@PathVariable int group_id){
        List<GroupNotice> notices = groupNoticeService.getList(group_id);
        return new ResponseDto<List<GroupNotice>>(HttpStatus.OK.value(), notices);
    }

    @PostMapping("/group/{group_id}/notice")
    public ResponseDto<GroupNotice> createNotice(@PathVariable int group_id,
                                                 @RequestBody GroupNoticeDTO groupNoticeDTO ){
        GroupNotice groupNotice = groupNoticeService.create(group_id, groupNoticeDTO);
        return new ResponseDto<>(HttpStatus.OK.value(), groupNotice);
    }
    @PutMapping("/group/{group_id}/notice/{notice_id}")
    public ResponseDto<GroupNotice> updateNotice(@PathVariable int group_id,
                                                 @PathVariable int notice_id,
                                                 @RequestBody GroupNoticeDTO groupNoticeDTO) {
        GroupNotice updatedNotice = groupNoticeService.update(group_id, notice_id, groupNoticeDTO);
        return new ResponseDto<>(HttpStatus.OK.value(), updatedNotice);
    }

    @DeleteMapping("/group/{group_id}/notice/{notice_id}")
    public ResponseDto<String> deleteNotice(@PathVariable int group_id, @PathVariable int notice_id) {
        groupNoticeService.delete(group_id, notice_id);
        return new ResponseDto<>(HttpStatus.OK.value(), "Notice deleted successfully");
    }
}

