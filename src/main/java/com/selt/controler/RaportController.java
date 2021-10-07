package com.selt.controler;

import com.selt.model.Magazine;
import com.selt.model.Raport;
import com.selt.model.Temp;
import com.selt.service.RaportService;
import com.selt.service.TempService;
import com.selt.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@Data
public class RaportController {

    private final RaportService raportService;
    private final TempService tempService;
    private final UserService userService;

    @GetMapping({"/Raport"})
    public String getRaport(Model model) {
        model.addAttribute("temp", new Temp());
        model.addAttribute("username", userService.findUserByUsername().getFullname());
        return "/Raport";
    }

    @PostMapping({"/Raport"})
    public String getDate(@ModelAttribute("temp") Temp temp, Model model) {

        List<Raport> raport = null;
        String mattern = '%' + temp.getTempString() + '%';
        if (temp.getRadio() == 0) {
            raport = raportService.findAllByPreviousMonth();
        }
        if (temp.getRadio() == 2) {
            raport = raportService.findAll();
        }
        if (temp.getRadio() == 1) {
            raport = raportService.findAllByActualMonth();
        }

        if (temp.getRadio() == 3) {
            raport = raportService.findAllByDateBetween(LocalDate.parse(temp.getStart()), LocalDate.parse(temp.getEnd()));
        }
        if (temp.getRadio() == 4) {
            raport = raportService.search(mattern);
        }
        if (temp.getRadio() == 5) {
            raport = raportService.search(mattern);
        }
        if (temp.getRadio() == 6) {
            raport = raportService.findAllByPrinters_Toner_TonerNameIsLike(mattern);
        }
        if (temp.getRadio() == 7) {
            raport = raportService.findAllByPrinters_Department_NameOfDepartmentIsLike(mattern);
        }
        model.addAttribute("raport", raport);
        getRaport(model);
        return "/Raport";

    }

    @ResponseBody
    @GetMapping({"/showRaport"})
    public List<Raport> getRaport() {
        return raportService.findAll();
    }
}
