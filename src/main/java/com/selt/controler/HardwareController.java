package com.selt.controler;

import com.selt.model.Laptop;
import com.selt.model.Role;
import com.selt.model.UserRole;
import com.selt.model.Windows;
import com.selt.repository.WindowsRepo;
import com.selt.service.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class HardwareController {

//    UserRole userRole2 = new UserRole();
//			userRole2.setRole(Role.USER);
//			roleRepository.save(userRole2);

    @Autowired
    private final LaptopService laptopService;
    private final WindowsRepo windowsRepo;
    //public Windows windows = new Windows();



    @GetMapping({"/addLaptop"})
    public String addLaptopPage(Model model) {
        model.addAttribute("laptop", new Laptop());
        return "/addLaptop";
    }

    @PostMapping({"/addLaptop"})
    public String saveLaptop(@ModelAttribute("laptop")  Laptop laptop, Windows windows){

        //admin.setRoles(Arrays.asList(userRole,userRole2));

        windows.setWindowsKey("WYPNQ-8C467-V2W6J-TX4WX-WT2RQ");
        windows.setWindowsVersion("10 PRO");
        windowsRepo.save(windows);
        laptop.setWindowsKey(windows);
        laptopService.save(laptop);
        return "/index";
    }


}
