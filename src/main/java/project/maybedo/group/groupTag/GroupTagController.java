package project.maybedo.group.groupTag;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.maybedo.dto.ResponseDto;
import project.maybedo.group.GroupRepository;
import project.maybedo.group.GroupService;
import project.maybedo.group.domain.Group;
import project.maybedo.group.groupTag.groupTagDTO.GroupTagRequsetDTO;
import project.maybedo.tag.Tag;
import project.maybedo.tag.TagRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupTagController {

    private final TagRepository tagRepository;
    private final GroupRepository groupRepository;

    @GetMapping("/find/tag/groups/{content}")
    public ResponseDto<List<Group>> findGroupWithTag (@PathVariable String content)
    {
        Tag tag = tagRepository.findByContent(content);
        int id = tag.getId();

        List<Group> tagGroup = groupRepository.findByGroupTags_TagId(id);

        for (Group group : tagGroup) {
            System.out.println("Group ID: " + group.getId());
            System.out.println("Group Name: " + group.getName());
        }


        return new ResponseDto<List<Group>>(HttpStatus.OK.value(), tagGroup);
    }
}
