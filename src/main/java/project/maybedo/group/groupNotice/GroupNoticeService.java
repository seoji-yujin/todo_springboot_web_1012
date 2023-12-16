package project.maybedo.group.groupNotice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.group.GroupRepository;
import project.maybedo.group.domain.Group;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupNoticeService {
    private final GroupNoticeRepository groupNoticeRepository;
    private final GroupRepository groupRepository;

    public List<GroupNotice> getList(int group_id)
    {
        //group_id가 존재하는지 확인
        List<GroupNotice> groupNotices = groupNoticeRepository.findByGroupId(group_id);
        return (groupNotices);
    }

    public GroupNotice create(int group_id, GroupNoticeDTO groupNoticeDTO)
    {
        Group group = groupRepository.findById(group_id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + group_id));

        GroupNotice groupNotice = new GroupNotice();
        groupNotice.setGroup(group);
        groupNotice.setContent(groupNoticeDTO.getContent());
        groupNoticeRepository.save(groupNotice);
        return (groupNotice);
    }
    public GroupNotice update(int group_id, int notice_id, GroupNoticeDTO groupNoticeDTO) {
        GroupNotice groupNotice = groupNoticeRepository.findByGroup_IdAndId(group_id, notice_id);
        groupNotice.setContent(groupNoticeDTO.getContent());
        return (groupNoticeRepository.save(groupNotice));
    }

    public void delete(int group_id, int notice_id) {
        GroupNotice groupNotice = groupNoticeRepository.findByGroup_IdAndId(group_id, notice_id);
        groupNoticeRepository.delete(groupNotice);
    }
}
