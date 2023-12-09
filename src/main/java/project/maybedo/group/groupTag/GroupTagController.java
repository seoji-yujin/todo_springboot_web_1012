package project.maybedo.group.groupTag;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.system.SystemProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.maybedo.dto.ResponseDto;
import project.maybedo.group.GroupRepository;
import project.maybedo.group.GroupService;
import project.maybedo.group.domain.Group;
import project.maybedo.group.groupTag.groupTagDTO.GroupTagRequsetDTO;
import project.maybedo.tag.Tag;
import project.maybedo.tag.TagRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class GroupTagController {

    private final TagRepository tagRepository;
    private final GroupRepository groupRepository;

    @GetMapping("/find/tag/groups")
    public ResponseDto<List<Group>> findGroupWithTag (@RequestParam List<String> tags)
    {
        Set<Group> tagGroups = new HashSet<>();

        for(String tagContent : tags)
        {
            Tag tag = tagRepository.findByContent(tagContent);
            if (tag != null)
            {
                int tagId = tag.getId();
                List<Group> groups = groupRepository.findByGroupTags_TagId(tagId);
                tagGroups.addAll(groups);
            }
        }

        return new ResponseDto<List<Group>>(HttpStatus.OK.value(), new ArrayList<>(tagGroups));
    }
}
