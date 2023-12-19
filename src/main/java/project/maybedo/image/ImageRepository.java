package project.maybedo.image;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.member.Member;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findByMember(Member member);
}
