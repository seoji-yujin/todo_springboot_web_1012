package project.maybedo.maybedo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.dto.ResponseDto;
import project.maybedo.member.Member;
import project.maybedo.member.MemberService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MaybedoController {

    private final MaybedoService maybedoService;

    //maybedo 리스트 조회
    @GetMapping("/maybedo")
    public ResponseDto<List> list() {
        return new ResponseDto<List>(HttpStatus.OK.value(), maybedoService.getList());
    }

    //오늘의 maybedo 리스트 조회
    @GetMapping("/today/maybedo")
    public ResponseDto<List> todayMaybedoList() {
        LocalDate today = LocalDate.now();
        List<Maybedo> todayMabyedoList = maybedoService.getTodayList(today);
        return new ResponseDto<List>(HttpStatus.OK.value(), todayMabyedoList);
    }

    //maybedo 작성
    @PostMapping("/maybedo/create")
    public ResponseDto<Maybedo> createMaybedo(@RequestBody MaybedoCreateDTO maybedoCreateDTO, HttpSession httpSession) {
        Member member = (Member)httpSession.getAttribute("principal");
        Maybedo maybedo = maybedoService.create(member, maybedoCreateDTO);
        return new ResponseDto<Maybedo>(HttpStatus.OK.value(), maybedo);
    }

    //maybedo 수정
    @PutMapping("/maybedo/update/{id}")
    public ResponseDto<Maybedo> updateMaybedo(@PathVariable int id, @RequestBody MaybedoCreateDTO maybedoCreateDTO) {
        Maybedo maybedo = maybedoService.update(id, maybedoCreateDTO);
        return new ResponseDto<Maybedo>(HttpStatus.OK.value(), maybedo);
    }

    //maybedo 삭제
    @DeleteMapping("/maybedo/delete/{id}")
    public ResponseDto<Integer> deleteMaybedo(@PathVariable int id) {
        maybedoService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), id);
    }
}
