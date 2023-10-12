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

    @Autowired
    private BCryptPasswordEncoder encoder;


    /**
     * 멤버 생성
     */
//    public int join(MemberFormDTO memberFormDTO) {
//        Member member = new Member();
//
//        member.setName(memberFormDTO.getName());
//        member.setEmail(memberFormDTO.getEmail());
//        member.setPasswd(memberFormDTO.getPasswd());
//        member.setMessage(memberFormDTO.getMessage());
//        member.setPhoto_url(memberFormDTO.getPhoto_url());
//
//        return memberRepository.save(member).getId();
//    }

    public int join(Member member) {  // 근데 이렇게 받아와서 저장이 되는지 궁금함
        String rawPassword = member.getPasswd();   // 진짜 비번
        String encPassword = encoder.encode(rawPassword);  // 비번이 해쉬값으로 바뀌고
        member.setPasswd(encPassword);
        return memberRepository.save(member).getId();
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
