package com.selt.controler;

import com.selt.model.Laptop;
import com.selt.model.Role;
import com.selt.model.UserRole;
import com.selt.model.Windows;
import com.selt.repository.WindowsRepo;
import com.selt.service.LaptopService;
import com.selt.service.WindowsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor

public class HardwareController {



    private final LaptopService laptopService;

    private final WindowsService windowsService;

   // private final Model model;


   // private final WindowsRepo windowsRepo;

    @GetMapping({"/showLaptops"})
    public List<Laptop> getLaptops(){
        return laptopService.findAl();
    }


    @GetMapping({"/addLaptop"})
    public String addLaptopPage(Model model) {
        model.addAttribute("laptop", new Laptop());
        List<Windows> windowsKeys = windowsService.findAll();
        model.addAttribute("Keys",windowsKeys);
        return "/addLaptop";
    }

    @PostMapping({"/addLaptop"})
    public String saveLaptop(@ModelAttribute("laptop")  Laptop laptop){

        laptopService.save(laptop);
        return "/index";
    }


}
