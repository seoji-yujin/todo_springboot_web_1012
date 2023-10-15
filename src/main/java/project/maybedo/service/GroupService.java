package project.maybedo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.domain.Group;
import project.maybedo.domain.Member;
import project.maybedo.domain.Todo;
import project.maybedo.repository.GroupRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    //  그룹 생성
    public void create(Group group, Member member) {
        Group new_group = new Group();
        new_group.setLeader(member.getId());  // 그룹장 아이디
        new_group.setName(group.getName());  // 그룹 이름
        new_group.setExpire_date(LocalDateTime.now());
        new_group.setDescription(group.getDescription());  // 그룹 소개
        new_group.setLimit_member(group.getLimit_member());  // 그룹 최대 인원
        new_group.setPhoto_url(group.getPhoto_url());  // 그룹 대표 사진
        this.groupRepository.save(new_group);
    }

    // 그룹 삭제
    public void delete(int group_id, int member_id) {
        Group group = groupRepository.findById(group_id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹 : " + group_id));
        if (group.getLeader() == member_id)  // 그룹장과 삭제하려는 사람의 아이디가 같으면
        {
            groupRepository.delete(group);
        }
    }

}
