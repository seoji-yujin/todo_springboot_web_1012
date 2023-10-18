package project.maybedo.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public int join(Member member) {
        Member findMember = memberRepository.findByUsername(member.getUsername());
        if (findMember == null)
            return memberRepository.save(member).getId();
        return (-1);
    }

    // 중복 유저 체크
    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByUsername(member.getUsername());
        if (findMember != null)
            throw new IllegalStateException("이미 가입된 회원입니다.");
    }
    
    @Transactional(readOnly = true)  // select할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
    public Member login(Member member) {
        return memberRepository.findByUsernameAndPassword(member.getUsername(), member.getPassword());
    }

    /**
     * 전체 멤버 조회
     */
    public List<Member> getList() {
        return memberRepository.findAll();
    }

    /**
     * 멤버 조회
     */
    public Member getMember(int id) {
        Member m = memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        return (m);
    }

}
