package project.maybedo.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.group.domain.Group;
import project.maybedo.group.groupJoin.Join;
import project.maybedo.group.groupJoin.JoinRepository;
import project.maybedo.group.groupTag.GroupTag;
import project.maybedo.group.groupTag.GroupTagRepository;
import project.maybedo.member.Member;
import project.maybedo.tag.Tag;
import project.maybedo.tag.TagRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final JoinRepository joinRepository;
    private final TagRepository tagRepository;   // 태그 저장하는
    private final GroupTagRepository groupTagRepository;  // 태그와 그룹 함께 저장하는

    // 전체 그룹 조회
    public List<Group> getAll(){
        return groupRepository.findAll();
    }

    // 그룹 1개 조회
    public Optional<Group> getGroup(int group_id) {
        return groupRepository.findById(group_id);
    }

    // 그룹 가입
    public void joinGroup(int group_id, Member member)
    {
        // 그룹 찾아오고
        Group group = groupRepository.findById(group_id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 그룹 : " + group_id));
        System.out.println(group.getId());
        Join join = new Join();
        join.setGroup(group);
        join.setMember(member);
        join.setDate(LocalDate.now());
        joinRepository.save(join);
        // 가입 했으면 그룹 cur_member 인원 수 올려주기
        Integer cur_member = group.getCur_member();
        cur_member++;
        group.setCur_member(cur_member);
        groupRepository.save(group);
    }

    //  그룹 생성
    public Group create(GroupCreateDTO groupCreateDTO, Member member) {
        Group new_group = new Group();
        new_group.setLeader(member.getId());  // 그룹장 아이디
        new_group.setName(groupCreateDTO.getName());  // 그룹 이름
        new_group.setExpire_date(LocalDate.now());
        new_group.setDescription(groupCreateDTO.getDescription());  // 그룹 소개
        new_group.setLimit_member(groupCreateDTO.getLimit_member());  // 그룹 최대 인원
        new_group.setPhoto_url(groupCreateDTO.getPhoto_url());  // 그룹 대표 사진
        new_group.setCur_member(0);

        // 해시태그 저장
        List<String> tagContents = groupCreateDTO.getTag();
        for (String tagContent : tagContents) {
            // 태그를 찾거나 새로 생성
            Tag tag = tagRepository.findByContent(tagContent);
            if (tag == null) {
                tag = new Tag();
                tag.setContent(tagContent);
                tagRepository.save(tag);
            }

            // 그룹과 태그 연결
            GroupTag groupTag = new GroupTag();
            groupTag.setGroup(new_group);
            groupTag.setTag(tag);
            new_group.getGroupTags().add(groupTag);
            groupTagRepository.save(groupTag);
        }
        groupRepository.save(new_group);
        joinGroup(new_group.getId(), member); // 그룹을 생성한 그룹장도 그룹 멤버로 가입

        return new_group;
    }

    // 그룹의 멤버 찾아오기
    public List<Member> findMemberInGroup(int group_id)
    {
        List<Join> joinList = joinRepository.findByGroup_Id(group_id);
        // joinList에서 member 뽑아서 반환
        List<Member> membersInGroup = new ArrayList<Member>();
        for (Join join : joinList) {
            membersInGroup.add(join.getMember());
        }
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
