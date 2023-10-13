package project.maybedo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.maybedo.dto.MemberFormDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int id;
    private String name;
    private String email;
    private String passwd;
    private String message;
    private String photo_url;
    @OneToMany(mappedBy = "member")
    private List<Todo> todo_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Maybedo> maybedo_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Schedule> schedule_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Message> message_list = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Join> join_list = new ArrayList<>();


    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){

        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        // 스프링 시큐리티 설정 클래스에 등록한 BcryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호를 암호화
        String password = passwordEncoder.encode(memberFormDto.getPasswd());
        member.setPasswd(password);
        return member;
    }

}
