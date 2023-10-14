package project.maybedo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.maybedo.domain.Member;
import project.maybedo.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

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

    /**
     * 로그인
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null) // 가입되지 않은 메일 주소인 경우
            throw new UsernameNotFoundException(email);

        return User.builder()
                .username(member.getEmail()) // 회원가입 기준이 되는 Member 객체의 이메일을 User객체의 name에 설정
                .password(member.getPasswd()) // Member 객체의 비밀번호를 User객체에도 동일하게 설정
                .build();
    }
}
