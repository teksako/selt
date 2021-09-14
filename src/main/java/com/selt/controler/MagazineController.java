package com.selt.controler;

import com.selt.model.*;
import com.selt.repository.TonerRepo;
import com.selt.service.MagazineService;
import com.selt.service.TonerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class MagazineController {

    private final TonerService tonerService;
    private final MagazineService magazineService;

    @GetMapping({"/Magazine"})
    public String userPage(Model model){
        model.addAttribute("tonerek",new TonerMagazine());
        List<Toner> tonerList=tonerService.findAll();
        model.addAttribute("tonerList", tonerList);
        return "/Magazine";
    }

    @PostMapping({"/Magazine"})
    public String saveLocation(TonerMagazine tonerMagazine){
        magazineService.addNew(tonerMagazine);
        return "/Magazine";
    }


}

