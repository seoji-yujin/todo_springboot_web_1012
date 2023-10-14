package project.maybedo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.controller.dto.ResponseDto;
import project.maybedo.domain.Maybedo;
import project.maybedo.domain.Member;
import project.maybedo.repository.MaybedoRepository;
import project.maybedo.service.MaybedoService;
import project.maybedo.service.MemberService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MaybedoController {

    private final MaybedoService maybedoService;
    private final MemberService memberService;

    //maybedo 리스트 조회
    @RequestMapping("/maybedo")
    public ResponseDto<Integer> list(Model model) {
        List<Maybedo> maybedoList = maybedoService.getList();
        model.addAttribute("maybedoList", maybedoList);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //maybedo 작성
    @PostMapping("/maybedo/create")
    public ResponseDto<Integer> createMaybedo(@RequestBody String content, HttpSession httpSession) {
        Member member = (Member)httpSession.getAttribute("principal");
        maybedoService.create(member, content);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //maybedo 삭제
    @DeleteMapping("/maybedo/delete/{id}")
    public ResponseDto<Integer> deleteMaybedo(@PathVariable Integer id) {
        maybedoService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //maybedo 수정
    @PutMapping("/maybedo/update/{id}")
    public ResponseDto<Integer> updateMaybedo(@RequestParam String content, @PathVariable Integer id) {
        maybedoService.update(id, content);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
