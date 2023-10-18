package project.maybedo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.domain.Member;
import project.maybedo.repository.JoinRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final JoinRepository joinRepository;
    public List<Member> findMembersByGroup(Integer groupId)
    {
        return joinRepository.findMembersByGroupId(groupId);
    }
}
