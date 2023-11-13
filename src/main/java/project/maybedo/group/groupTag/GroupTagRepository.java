package project.maybedo.group.groupTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.maybedo.group.domain.Group;
import project.maybedo.group.groupJoin.Join;

import java.util.List;

public interface GroupTagRepository extends JpaRepository<GroupTag, Integer> {
//    List<Group> findGroupsByTagId(int tagId);
}
