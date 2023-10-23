package project.maybedo.maybedo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maybedo.member.Member;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MaybedoService {
    private final MaybedoRepository maybedoRepository;

    public List<Maybedo> getList() {
        return maybedoRepository.findAll();
    }

    public Maybedo create(Member member, MaybedoCreateDTO maybedoCreateDTO){
        Maybedo maybedo = new Maybedo();
        maybedo.setMember(member);
        maybedo.setContent(maybedoCreateDTO.getContent());
        maybedo.setDate(LocalDate.now());
        return maybedoRepository.save(maybedo);
    }

    public void delete(int id) {
        Maybedo maybedo = maybedoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 maybedo : " + id));
        maybedoRepository.delete(maybedo);
    }

    public Maybedo update(int id, MaybedoCreateDTO maybedoCreateDTO) {
        Maybedo maybedo = maybedoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 maybedo : " + id));
        maybedo.setContent(maybedoCreateDTO.getContent());
        return maybedoRepository.save(maybedo);
    }
}
