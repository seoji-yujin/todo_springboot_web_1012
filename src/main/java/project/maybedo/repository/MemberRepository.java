package project.maybedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
