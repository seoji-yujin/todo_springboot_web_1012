package project.maybedo.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.maybedo.dto.ResponseDto;
import project.maybedo.tag.tagDTO.TagCreateDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/create/tag")
    public ResponseDto<Tag> createTag(@RequestBody TagCreateDTO tagCreateDTO)
    {
        Tag new_tag = tagService.createTag(tagCreateDTO);
        return new ResponseDto<Tag>(HttpStatus.OK.value(), new_tag);
    }
}
