package project.maybedo.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.group.domain.Group;
import project.maybedo.group.groupJoin.Join;
import project.maybedo.group.groupJoin.JoinRepository;
import project.maybedo.member.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final JoinRepository joinRepository;

    // 그룹 가입
    public void joinGroup(int group_id, Member member)
    {
        // 그룹 찾아오고
        Group group = groupRepository.findById(group_id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹 : " + group_id));

        Join join = new Join();
        join.setGroup(group);
        join.setMember(member);
        join.setDate(LocalDate.now());

        joinRepository.save(join);
    }

    //  그룹 생성
    public void create(Group group, Member member) {
        Group new_group = new Group();
        new_group.setLeader(member.getId());  // 그룹장 아이디
        new_group.setName(group.getName());  // 그룹 이름
        new_group.setExpire_date(LocalDate.now());
        new_group.setDescription(group.getDescription());  // 그룹 소개
        new_group.setLimit_member(group.getLimit_member());  // 그룹 최대 인원
        new_group.setPhoto_url(group.getPhoto_url());  // 그룹 대표 사진
        this.groupRepository.save(new_group);

        joinGroup(new_group.getId(), member);
    }

    // 그룹의 멤버 찾아오기
    public List<Member> findMemberInGroup(int group_id)
    {
        List<Join> joinList = joinRepository.findByGroup_Id(group_id);

        // joinList에서 member 뽑아서 반환
        List<Member> membersInGroup = joinList.stream()
                .map(Join::getMember)
                .collect(Collectors.toList());

        return membersInGroup;
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