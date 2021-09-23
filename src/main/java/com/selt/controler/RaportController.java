package com.selt.controler;

import com.selt.model.Magazine;
import com.selt.model.Raport;
import com.selt.service.RaportService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Data
public class RaportController {

    private final RaportService raportService;

    @ResponseBody
    @GetMapping({"/showRaport"})
    public List<Raport> getRaport() {
        return raportService.findAll();
    }
}
