package com.selt.controler;

import com.selt.model.*;
import com.selt.service.MagazineService;
import com.selt.service.PrinterService;
import com.selt.service.TonerService;
import com.selt.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserService userService;

    @GetMapping({"/Magazine"})
    public void userPage(Model model) {
        model.addAttribute("temp", new Temp());
        model.addAttribute("magazine", new Magazine());
        model.addAttribute("printers", new Printer());
        model.addAttribute("username",  userService.findUserByUsername().getFullname());
        //model.addAttribute("counter", number);
        //List<Toner> tonerList = tonerService.findAll();
        List<Magazine> magazines = magazineService.findAll();
        model.addAttribute("tonerList", magazines);
        List<Printer> printerList = printerService.findAll();
        model.addAttribute("printer", printerList);

    }

    @PostMapping({"/addMagazine"})
    public String addToner(@ModelAttribute("magazine") Magazine magazine, Model model) {
        //String allert="Ujemna liczba!";
        if (magazine.getCount() < 1l) {
            model.addAttribute("allert", "Ujemna liczba!");
        } else {
            magazineService.updateInventory(magazine, magazineService.getActualCount(magazine));
        }
        userPage(model);
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
        if (magazine.getCount() > magazineService.getActualCount(magazine)) {
            model.addAttribute("allert", "Nie masz tyle na stanie!");
        } else {

            magazineService.removeInventory(magazine, magazineService.getActualCount(magazine));
        }
        userPage(model);
        return "/Magazine";

    }

    @PostMapping({"/getMagazine"})
    public String showMagazine(@ModelAttribute("temp") Temp temp, Model model) {
        String matterm = '%' + temp.getTempString() + '%';
        List<Magazine> foundMagazines = magazineService.findAllMagazinesByToner_TonerNameIsLike(matterm);

        if (foundMagazines.size() == 0) {

            model.addAttribute("error", "Nie ma takiego toneru");
        }
        model.addAttribute("tonerLists", foundMagazines);
        userPage(model);
        return "/Magazine";
    }

    @ResponseBody
    @GetMapping({"/showMagazine"})
    public List<Magazine> getMagazine() {
        return magazineService.findAll();
    }

}

