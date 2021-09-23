package com.selt.controler;

import com.selt.model.*;
import com.selt.service.MagazineService;
import com.selt.service.PrinterService;
import com.selt.service.TonerService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Data
public class MagazineController {

    private final TonerService tonerService;
    private final MagazineService magazineService;
    private final PrinterService printerService;

    @GetMapping({"/Magazine"})
    public String userPage(Model model, Magazine magazine) {

       model.addAttribute("magazine", new Magazine());
        model.addAttribute("printers", new Printer());
        //model.addAttribute("counter", number);
        //List<Toner> tonerList = tonerService.findAll();
        List<Magazine> magazines = magazineService.findAll();
        model.addAttribute("tonerList", magazines);
        List<Printer> printerList = printerService.findAll();
        model.addAttribute("printer", printerList);
        return "/Magazine";
    }

    @PostMapping({"/addMagazine"})
    public String addToner(@ModelAttribute("magazine") Magazine magazine, Model model) {
        //String allert="Ujemna liczba!";
        if (magazine.getCount() < 1l) {
            model.addAttribute("allert", "Ujemna liczba!");
        } else {
            magazineService.updateInventory(magazine, magazineService.getActualCount(magazine));
        }
        return "/Magazine";

    }

//  ----pierwotna wersja--------------------------------------------------------------------------------
//    @PostMapping({"/removeMagazine"})
//    public String removeToner(@ModelAttribute("magazine") Magazine magazine, Model model, Printer printer) {
//        //String allert="Ujemna liczba!";
//    // magazineService.getFromPrinter(printer);
//        if(magazine.getCount()>magazineService.getActualCount(magazine)){
//            model.addAttribute("allert", "Nie masz tyle na stanie!");
//        }
//        else {
//            magazineService.removeInventory(magazine, magazineService.getActualCount(magazine));
//        }
//        return "/Magazine";
//
//    }

    @PostMapping({"/removeMagazine"})
    public String removeToner(@ModelAttribute("magazine") Magazine magazine, Model model) {
        if(magazine.getCount()>magazineService.getActualCount(magazine)){
            model.addAttribute("allert", "Nie masz tyle na stanie!");
        }
        else {

            magazineService.removeInventory(magazine, magazineService.getActualCount(magazine));
        }
        return "/Magazine";

    }

    @ResponseBody
    @GetMapping({"/showMagazine"})
    public List<Magazine> getMagazine() {
        return magazineService.findAll();
    }

}

