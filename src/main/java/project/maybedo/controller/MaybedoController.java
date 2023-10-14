package project.maybedo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.maybedo.domain.Maybedo;
import project.maybedo.repository.MaybedoRepository;
import project.maybedo.service.MaybedoService;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MaybedoController {

    private final MaybedoService maybedoService;

    @RequestMapping("/maybedo")
    public String list(Model model) {
        List<Maybedo> maybedoList = this.maybedoService.getList();
        model.addAttribute("maybedoList", maybedoList);
        return "maybedolist";
    }

//    @RequestMapping("/")
//    public String root() {
//        return "main";
//    }

    @PostMapping("/maybedo/create")
    public String createMaybedo(@RequestParam String content) {
        this.maybedoService.create(content);
        return "redirect:/maybedo";
    }

    @DeleteMapping("/maybedo/delete/{id}")
    public String deleteMaybedo(@PathVariable Integer id) {
        this.maybedoService.delete(id);
        return "redirect:/maybedo";
    }

    @PutMapping("/maybedo/update/{id}")
    public String updateMaybedo(@RequestParam String content, @PathVariable Integer id) {
        this.maybedoService.update(id, content);
        return "redirect:/maybedo";
    }

}
