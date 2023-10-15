package project.maybedo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.domain.Maybedo;
import project.maybedo.domain.Member;
import project.maybedo.repository.MaybedoRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MaybedoService {
    private final MaybedoRepository maybedoRepository;

    public List<Maybedo> getList() {
        return this.maybedoRepository.findAll();
    }
    public void create(Member member, String content){
        Maybedo maybedo = new Maybedo();
        maybedo.setMember(member);
        maybedo.setContent(content);
        maybedo.setDate(new Date());
        this.maybedoRepository.save(maybedo);
    }

    public void delete(Integer id) {
        Maybedo maybedo = maybedoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        this.maybedoRepository.delete(maybedo);
    }

    public void update(Integer id, String content) {
        Maybedo maybedo = maybedoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 id : " + id));
        maybedo.setContent(content);
        this.maybedoRepository.save(maybedo);
    }
}
