package project.maybedo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.controller.dto.GroupDto;
import project.maybedo.domain.Group;
import project.maybedo.domain.Join;
import project.maybedo.domain.Member;
import project.maybedo.repository.GroupRepository;
import project.maybedo.repository.JoinRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final JoinRepository joinRepository;

    /**
     * 그룹 생성
     */
    @Transactional
    public void create(Member member , GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        group.setLimit_member(groupDto.getLimit_member());
        group.setDescription(groupDto.getDescription());
        group.setPhoto_url(groupDto.getPhoto_url());
        groupRepository.save(group);

        Join join = new Join();
        join.setDate(LocalDateTime.now());
        join.setLeader(Boolean.TRUE);
        join.setMember(member);
        join.setGroup(group);
        System.out.println(group.getJoin_list());
        // group join_list에 해당 멤버 추가해야함..
        joinRepository.save(join);
    }

    /**
     * 그룹에 멤버 추가
     */


}
