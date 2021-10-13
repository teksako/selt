package com.selt.controler;

import com.selt.model.*;
import com.selt.repository.WindowsRepo;
import com.selt.service.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller

@RequiredArgsConstructor

public class HardwareController {

    private final DepartmentService departmentService;
    private final LaptopService laptopService;
    private final EmployeeService employeeService;
    private final WindowsService windowsService;
    private final OfficeService officeService;
    private final ComputerService computerService;
    private final TonerService tonerService;
    private final MobilePhoneService mobilePhoneService;
    private final PhoneNumberService phoneNumberService;
    private final PrinterService printerService;
    private final UserService userService;


    @ResponseBody
    @GetMapping({"/showLaptops"})
    public List<Laptop> getLaptops() {
        return laptopService.findAll();
    }


    @GetMapping({"/showUserHardware"})
    public String getHardwares(Model model) {

        List<Computer> computerList = computerService.findAllByEmployee();
        List<Laptop> laptopList = laptopService.findAllByEmployee();
        model.addAttribute("computer", computerList);
        model.addAttribute("laptop", laptopList);
        return "/showUserHardware";
    }

    @GetMapping({"/addPrinter"})
    public String addPrinterPage(Model model){
        model.addAttribute("username",  userService.findUserByUsername().getFullname());
        model.addAttribute("printer", new Printer());
        List<Department> departmentList = departmentService.findAll();
        List<Toner> tonerList=tonerService.findAll();
        model.addAttribute("toners", tonerList);
        model.addAttribute("departments", departmentList);
        return "/addPrinter";
    }

    @PostMapping({"/addPrinter"})
    public String savePrinter(@ModelAttribute("printer") Printer printer, Model model){
        addPrinterPage(model);
        printerService.save(printer);
        return "/addPrinter";
    }

    @ResponseBody
    @GetMapping({"/showPrinters"})
    public List<Printer>showPrinters(){
        return printerService.findAll();
    }




    @GetMapping({"/addPhone"})
    public String addPhonePage(Model model) {
        List<PhoneNumber>phoneNumbers=phoneNumberService.findAll();
        model.addAttribute("phonenumber", phoneNumbers);
        model.addAttribute("phone", new MobilePhone());
        return "/addPhone";
    }

    @PostMapping({"/addPhone"})
    public String savePhone(@ModelAttribute("phone") MobilePhone mobilePhone) {
        mobilePhoneService.save(mobilePhone);
        return "/addPhone";
    }


    @GetMapping({"/addLaptop"})
    public String addLaptopPage(Model model) {
        List<Windows> windowsKeys = windowsService.findAll();
        List<Office> officeKeys = officeService.findAll();
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("laptop", new Laptop());
        model.addAttribute("owners", employees);
        model.addAttribute("officeKeys", officeKeys);
        model.addAttribute("Keys", windowsKeys);
        return "/addLaptop";
    }

    @PostMapping({"/addLaptop"})
    public String saveLaptop(@ModelAttribute("laptop") Laptop laptop) {

        laptopService.save(laptop);
        return "/index";
    }

    @GetMapping({"/addComputer"})
    public String addComputerPage(Model model) {
        List<Windows> windowsKeys = windowsService.findAll();
        List<Office> officeKeys = officeService.findAll();
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("computer", new Computer());
        model.addAttribute("owners", employees);
        model.addAttribute("officeKeys", officeKeys);
        model.addAttribute("Keys", windowsKeys);
        return "/addComputer";
    }

    @PostMapping({"/addComputer"})
    public String saveComputer(@ModelAttribute("computer") Computer computer) {

        computerService.save(computer);
        return "/index";
    }


}
