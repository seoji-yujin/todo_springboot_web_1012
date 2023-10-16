package project.maybedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.domain.Group;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
