package project.maybedo.member;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.maybedo.group.GroupRepository;
import project.maybedo.group.GroupService;
import project.maybedo.group.domain.Group;
import project.maybedo.group.groupJoin.Join;
import project.maybedo.group.groupJoin.JoinRepository;
import project.maybedo.member.memberDTO.MemberJoinDTO;
import project.maybedo.member.memberDTO.MemberUpdateDTO;
import project.maybedo.todo.Todo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JoinRepository joinRepository;


    // 회원가입(email, username, password)
    @Transactional
    public int join(MemberJoinDTO memberJoinDTO) {
        Member findMember = memberRepository.findByUsername(memberJoinDTO.getUsername());
        if (findMember == null)
        {
            Member new_member = new Member();

            new_member.setName(memberJoinDTO.getName());
            new_member.setEmail(memberJoinDTO.getEmail());
            new_member.setUsername(memberJoinDTO.getUsername());
            new_member.setPassword(memberJoinDTO.getPassword());
            return memberRepository.save(new_member).getId();
        }
        return (-1);
    }

    // 중복 유저 체크
    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByUsername(member.getUsername());
        if (findMember != null)
            throw new IllegalStateException("이미 가입된 회원입니다.");
    }

    // 로그인
    @Transactional(readOnly = true)  // select할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
    public Member login(String username, String password) {
        return memberRepository.findByUsernameAndPassword(username, password);
    }

    // 회원 정보 수정
    public Member update(MemberUpdateDTO memberUpdateDTO, Member member)
    {
        Member update_member = memberRepository.findById(member.getId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 id : " + member.getId()));
        if (memberUpdateDTO.getName() != null)
            update_member.setName(memberUpdateDTO.getName());
        if (memberUpdateDTO.getEmail() != null)
            update_member.setEmail(memberUpdateDTO.getEmail());
        if (memberUpdateDTO.getMessage() != null)
            update_member.setMessage(memberUpdateDTO.getMessage());
//        if (memberUpdateDTO.getPhoto_url() != null)
//            update_member.setPhoto_url(memberUpdateDTO.getPhoto_url());
        memberRepository.save(update_member);
        return (update_member);
    }

    // 전체 멤버 조회
    public List<Member> getList() {
        return memberRepository.findAll();
    }

    // 멤버 조회
    public Member getMember(String username) {
        Member m = memberRepository.findByUsername(username);
        if(m == null) {
            throw new IllegalArgumentException("존재하지 않는 username : " + username);
        }
        return (m);
    }

    // 멤버가 가입된 그룹 리스트 가져오기
    public List<Group>  getMyGroups(Member member) {
        int member_id = member.getId();

        List<Join>  joinList = joinRepository.findByMember_Id(member_id);
        // joinList에서 group 뽑아서 반환
        List<Group> myGroups = new ArrayList<>();
        for (Join join : joinList) {
            myGroups.add(join.getGroup());
        }
        return (myGroups);
    }

    // 달성률 매일 밤 초기화
    @Scheduled(cron = "0 0 0 * * ?")  // 매일 밤 자정에 초기화 해주는 크론식
    public void resetAchievement() {
        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            member.setAchievement(0.0);
        }

        memberRepository.saveAll(members);
    }

}
