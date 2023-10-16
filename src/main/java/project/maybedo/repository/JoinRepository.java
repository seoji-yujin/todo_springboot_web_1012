package project.maybedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.domain.Join;
import project.maybedo.domain.Maybedo;

public interface JoinRepository extends JpaRepository<Join, Integer> {
}
