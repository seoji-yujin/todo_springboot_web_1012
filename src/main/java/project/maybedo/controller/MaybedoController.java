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
@RestController
public class MaybedoController {

    private final MaybedoService maybedoService;

    @RequestMapping("/maybedo")
    public List<Maybedo> list(Model model) {
        List<Maybedo> maybedoList = maybedoService.getList();
        model.addAttribute("maybedoList", maybedoList);
        return maybedoList;
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/maybedo";
    }

    @PostMapping("/maybedo/create")
    public Maybedo createMaybedo(@RequestParam String content) {
        Maybedo maybedo = maybedoService.create(content);
        return maybedo;
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
