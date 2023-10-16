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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final JoinRepository joinRepository;

    // 그룹 생성
    @Transactional
    public void create(Member member, GroupDto groupDto) {
        Group group = new Group();
        group.setLeader(member.getId());
        group.setName(groupDto.getName());
        group.setLimit_member(groupDto.getLimit_member());
        group.setDescription(groupDto.getDescription());
        group.setPhoto_url(groupDto.getPhoto_url());
        groupRepository.save(group);

        Join join = new Join();
        join.setGroup(group);
        join.setMember(member);
        join.setDate(LocalDateTime.now());
        join.setLeader(Boolean.TRUE);
        joinRepository.save(join);
    }

    // 그룹 세부 사항 설정
//    @Transactional
//    public void setDetail(GroupDetailDto groupDetailDto)
//    {
//
//    }

    // 그룹 삭제
    public void delete(Integer id)
    {
        Group group = groupRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹 " + id));
        groupRepository.delete(group);
    }

    // 그룹 가입
    public void joinGroup(Member member, Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹 " + id));

        Join join = new Join();
        join.setGroup(group);
        join.setMember(member);
        join.setDate(LocalDateTime.now());
        join.setLeader(Boolean.FALSE);
        joinRepository.save(join);
    }
}