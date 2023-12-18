package project.maybedo.group.groupJoin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinRepository extends JpaRepository<Join, Integer> {
    List<Join> findByGroup_Id(int group_id);
    List<Join> findByMember_Id(int member_id);
    Join findByMember_IdAndGroup_Id(int member_id, int group_id);
}
