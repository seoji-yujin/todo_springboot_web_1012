package project.maybedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.domain.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
