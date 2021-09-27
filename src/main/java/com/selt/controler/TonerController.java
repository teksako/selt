package com.selt.controler;

import com.selt.model.Toner;
import com.selt.service.TonerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TonerController {
    private final TonerService tonerService;

    @GetMapping({"/Toner"})
    public String tonerPage(Model model) {
        model.addAttribute("toner", new Toner());
        List<Toner> toners= tonerService.findAll();
        model.addAttribute("tonerList", toners);
        return "/Toner";
    }

    @PostMapping({"/addToner"})
    public String saveToner(@ModelAttribute("toner") Toner toner) {
        tonerService.save(toner);

        return "/Toner";
    }


//    @GetMapping({"/deleteToner"})
//    public String deleteTonerPage(Model model) {
//        model.addAttribute("toner", new Toner());
//        List<Toner> toners= tonerService.findAll();
//        model.addAttribute("tonerList", toners);
//        return "/deleteToner";
//    }
    @PostMapping({"/deleteToner"})
    public String deleteToner(@ModelAttribute("toner") Toner toner){
        tonerService.delete(toner);

        return "/Toner";
    }

//    @GetMapping({"/updateToner"})
//    public String updateTonerPage(Model model) {
//        model.addAttribute("toner", new Toner());
//        List<Toner> toners= tonerService.findAll();
//        model.addAttribute("tonerList", toners);
//        return "/updateToner";
//    }
    @PostMapping({"/updateToner"})
    public String saveUpdateToner(@ModelAttribute("toner") Toner toner,Model model){
        tonerService.update(toner);
        tonerPage(model);
        return "/Toner";
    }
}
