package project.maybedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByUsernameAndPassword(String username, String password);
}
