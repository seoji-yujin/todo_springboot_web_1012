package project.maybedo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.maybedo.domain.Member;
import project.maybedo.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public int join(Member member) {
        return memberRepository.save(member).getId();
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
