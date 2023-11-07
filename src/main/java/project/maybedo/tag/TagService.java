package project.maybedo.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.group.groupJoin.JoinRepository;
import project.maybedo.tag.tagDTO.TagCreateDTO;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag createTag(TagCreateDTO tagCreateDTO)
    {
        Tag new_tag = new Tag();
        new_tag.setContent(tagCreateDTO.getContent());
        tagRepository.save(new_tag);
        return (new_tag);
    }
}
