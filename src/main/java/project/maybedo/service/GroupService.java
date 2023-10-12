package project.maybedo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.domain.Group;
import project.maybedo.repository.GroupRepository;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    /**
     * 그룹 생성
     */
    public void groupCreate(Group group) {
        groupRepository.save(group);
    }

    /**
     * 그룹에 멤버 추가
     */


}
