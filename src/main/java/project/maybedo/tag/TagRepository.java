package project.maybedo.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.group.domain.Group;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByContent(String content);
}
