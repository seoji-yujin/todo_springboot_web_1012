package project.maybedo.group.join;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinRepository extends JpaRepository<Join, Integer> {
    List<Join> findByGroup_Id(int group_id);
}
