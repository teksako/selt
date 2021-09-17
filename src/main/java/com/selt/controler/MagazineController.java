package com.selt.controler;

import com.selt.model.*;
import com.selt.repository.TonerRepo;
import com.selt.service.MagazineService;
import com.selt.service.TonerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@Data
public class MagazineController {

    private final TonerService tonerService;
    private final MagazineService magazineService;

    @GetMapping({"/Magazine"})
    public String userPage(Model model, TonerMagazine tonerMagazine) {

        model.addAttribute("magazine", new TonerMagazine());
        //model.addAttribute("counter", number);
        //List<Toner> tonerList = tonerService.findAll();
        List<TonerMagazine> tonerMagazines=magazineService.findAll();
        model.addAttribute("tonerList", tonerMagazines);
        return "/Magazine";
    }

    @PostMapping({"/addMagazine"})
    public String addToner(@ModelAttribute("magazine") TonerMagazine tonerMagazine, Model model) {
        //String allert="Ujemna liczba!";
        if(tonerMagazine.getCount()<1l){
            model.addAttribute("allert", "Ujemna liczba!");
        }
            else {
            magazineService.updateInventory(tonerMagazine, magazineService.getActualCount(tonerMagazine));
        }
            return "/Magazine";

    }

    @PostMapping({"/removeMagazine"})
    public String removeToner(@ModelAttribute("magazine") TonerMagazine tonerMagazine, Model model) {
        //String allert="Ujemna liczba!";
        if(tonerMagazine.getCount()>magazineService.getActualCount(tonerMagazine)){
            model.addAttribute("allert", "Nie masz tyle na stanie!");
        }
        else {
            magazineService.removeInventory(tonerMagazine, magazineService.getActualCount(tonerMagazine));
        }
        return "/Magazine";

    }

    @ResponseBody
    @GetMapping({"/showMagazine"})
    public List<TonerMagazine> getMagazine() {
        return magazineService.findAll();
    }

}

