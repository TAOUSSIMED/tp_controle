package com.example.tp_controle.web;

import com.example.tp_controle.entities.Medecin;
import com.example.tp_controle.entities.Patient;
import com.example.tp_controle.repositories.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MedecinController {
    private MedecinRepository medecinRepository;

    @GetMapping(path="/user/medecin")
    public String medecins(Model model , @RequestParam(name = "page",defaultValue = "0")int page ,
                           @RequestParam(name = "size",defaultValue = "5")int size , @RequestParam(name = "keyword",defaultValue = "")String keyword )
    {
        Page<Medecin> pagemedecin = medecinRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listmedecins" , pagemedecin.getContent());
        model.addAttribute(  "pages", new int[pagemedecin.getTotalPages()]);
        model.addAttribute(  "currentPage", page);
        model.addAttribute(  "keyword", keyword);


        return "Medecins";
    }
    @GetMapping("/admin/medecin/delete")
    public String delete (Long id , String keyword , int page) {
        medecinRepository.deleteById (id);
        return "redirect:/user/medecin?page="+page+"&keyword="+keyword;

    }
    @GetMapping ("/admin/formMedecins")
    public String formMedecins(Model model){
        model.addAttribute("medecin" , new Medecin());
        return "formMedecins";
    }


}
