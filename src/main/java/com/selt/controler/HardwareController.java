package com.selt.controler;

import com.selt.model.*;
import com.selt.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping({"/printer"})
    public String printerPage(Model model){
        model.addAttribute("temp", new Temp());
        model.addAttribute("username",  userService.findUserByUsername().getFullname());
        model.addAttribute("printer", new Printer());
        List<Department> departmentList = departmentService.findAll();
        List<Toner> tonerList=tonerService.findAll();
//        List<Printer> printerList=printerService.findAll();
//        model.addAttribute("printers", printerList);
        model.addAttribute("toners", tonerList);
        model.addAttribute("departments", departmentList);
        return "printer";
    }

    @PostMapping({"/addPrinter"})
    public String savePrinter(@ModelAttribute("printer") Printer printer, Model model){
        printerPage(model);
        printerService.save(printer);
        return "/printer";
    }

//    @PostMapping({"/deletePrinter"})
//    public String deletePrinter(Printer printer, Model model){
//        printerPage(model);
//        printerService.delete(printer);
//        model.addAttribute("info", "Usunąłeś drukarkę "+ printer.getModel());
//        return "/printer";
//    }

    //      INNA METODA
    @GetMapping("/deletePrinter/{id}")
    public String deleteUser(@PathVariable (value = "id") long id, Model model) {
        printerService.deletePrinter(id);
        printerPage(model);
        return "/printer";
    }

    @PostMapping({"/printer"})
    public String getDate(@ModelAttribute("temp") Temp temp, Model model) {

        List<Printer> printerList= null;
        String mattern = '%' + temp.getTempString() + '%';

        if(temp.getTempString()==null) {
            if (printerList == null) {
                model.addAttribute("allert", "Brak danych!");
            }
            printerList = printerService.findAll();

        }
        else{
            printerList = printerService.findAllByModelIsLike(mattern);
        }
        model.addAttribute("printerList", printerList);
        printerPage(model);
        return "/printer";

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
