package com.selt.controler;

import com.selt.model.Magazine;
import com.selt.model.Raport;
import com.selt.model.Temp;
import com.selt.service.RaportService;
import com.selt.service.TempService;
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

    @GetMapping({"/Raport"})
    public String getRaport(Model model){
        model.addAttribute("temp", new Temp());
//        List<Raport> raport = null;
//        //List<Raport> raport=raportService.findAll();
//        if(temp.getRadio()==2) {
//            raport = raportService.findAll();
//        }
//        else if(temp.getRadio()==1){
//            raport=raportService.findAllByDateBetween();
//        }
//        model.addAttribute("raport", raport);


        return "/Raport";
    }

    @PostMapping({"/Raport"})
    public String getDate(@ModelAttribute("temp") Temp temp, Model model){
        //TimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        List<Raport> raport = null;
        //LocalDate start = temp.getStart();
        //String start = temp.getStart();
        //List<Raport> raport=raportService.findAll();
        if(temp.getRadio()==2) {
            raport = raportService.findAll();
        }
        if(temp.getRadio()==1){
            raport=raportService.findAllByActualMonth();
        }

        if(temp.getRadio()==3){
            //raport=raportService.findAllByDateBetween(LocalDate.parse(temp.getStart(), formatter),LocalDate.parse(temp.getEnd(),formatter));
            raport=raportService.findAllByDateBetween(LocalDate.parse(temp.getStart()),LocalDate.parse(temp.getEnd()));
        }
        if(temp.getRadio()==4) {
            String mattern = '%'+temp.getTempString()+'%';
            raport = raportService.search(mattern);
        }
        model.addAttribute("raport", raport);
        return "/Raport";
    }

    @ResponseBody
    @GetMapping({"/showRaport"})
    public List<Raport> getRaport() {
        return raportService.findAll();
    }
}
