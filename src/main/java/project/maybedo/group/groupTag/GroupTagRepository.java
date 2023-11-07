package project.maybedo.group.groupTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.maybedo.group.groupJoin.Join;

public interface GroupTagRepository extends JpaRepository<GroupTag, Integer> {
}
