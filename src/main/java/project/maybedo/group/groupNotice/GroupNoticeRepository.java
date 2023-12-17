package project.maybedo.group.groupNotice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupNoticeRepository extends JpaRepository<GroupNotice, Integer> {
    List<GroupNotice> findByGroupId(int group_id);

    GroupNotice findByGroup_IdAndId(int groupId, int noticeId);
}
