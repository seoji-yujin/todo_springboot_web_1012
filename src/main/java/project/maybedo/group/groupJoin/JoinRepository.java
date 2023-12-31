package project.maybedo.group.groupJoin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinRepository extends JpaRepository<Join, Integer> {
    List<Join> findByGroup_Id(int group_id);
    List<Join> findByMember_Id(int member_id);

    Join findByGroup_IdAndMember_Id(int group_id, int member_id);
}
