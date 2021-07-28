package com.selt.controler;

import com.selt.model.Office;
import com.selt.model.Windows;
import com.selt.service.OfficeService;
import com.selt.service.WindowsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LicenseController {

    private final WindowsService windowsService;
    private final OfficeService officeService;


    @GetMapping({"/addWindowsLicense"})
    public String windowsPage(Model model){
        model.addAttribute("windows", new Windows());
        return "addWindowsLicense";
    }

    @PostMapping("/addWindowsLicense")
    public String saveLicense(@ModelAttribute("windows") Windows windows, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addWindowsLicense";
        }
        windowsService.save(windows);
        return "index";
    }

    @GetMapping({"/addOfficeLicense"})
    public String officePage(Model model){
        model.addAttribute("office", new Office());
        return "addOfficeLicense";
    }

    @PostMapping({"addOfficeLicense"})
    public String saveOffice(Office office, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addOfficeLicense";
        }
        officeService.save(office);
        return "index";
    }
}
