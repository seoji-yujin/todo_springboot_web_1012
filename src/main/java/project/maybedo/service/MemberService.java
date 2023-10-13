package project.maybedo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.maybedo.domain.Member;
import project.maybedo.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 멤버 생성
     */
    public Member save(Member member){
        return memberRepository.save(member);
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
