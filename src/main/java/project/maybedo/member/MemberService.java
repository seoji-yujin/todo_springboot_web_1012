package project.maybedo.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.maybedo.member.memberDTO.MemberJoinDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


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
    
    @Transactional(readOnly = true)  // select할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
    public Member login(String username, String password) {
        return memberRepository.findByUsernameAndPassword(username, password);
    }

    // 전체 멤버 조회
    public List<Member> getList() {
        return memberRepository.findAll();
    }

    // 멤버 조회
    public Member getMember(int id) {
        Member m = memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        return (m);
    }

}
