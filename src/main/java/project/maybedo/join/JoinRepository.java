package project.maybedo.join;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.join.Join;

import java.util.List;

public interface JoinRepository extends JpaRepository<Join, Integer> {
    List<Join> findByGroup_Id(int group_id);
}
