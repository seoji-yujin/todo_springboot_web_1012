package project.maybedo.group;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.group.domain.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
