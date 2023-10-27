package project.maybedo.group;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.group.domain.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
