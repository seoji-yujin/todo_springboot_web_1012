package project.maybedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.domain.Join;
import project.maybedo.domain.Maybedo;

import java.util.List;

public interface JoinRepository extends JpaRepository<Join, Integer> {
    List<Join> findByGroup_Id(int group_id);
}
