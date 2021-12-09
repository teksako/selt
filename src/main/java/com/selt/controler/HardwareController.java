package com.selt.controler;

import com.selt.model.*;
import com.selt.repository.OIDRepo;
import com.selt.repository.PrinterRepo;
import com.selt.service.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private final PrinterRepo printerRepo;
    private final OIDRepo oidRepo;
    private final SNMP4J snmp4J;


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
        //---------------------OIDS-------------------------
        @PostMapping({"/saveOid"})
        public String savePrinter(@ModelAttribute OID oid) {
            oidRepo.save(oid);
            return "redirect:/list-printers";
        }

    //-------------------------PRINTERS----------------------------------
    @GetMapping({"/list-printers"})
    public ModelAndView getAllPrinters() {
        ModelAndView model = new ModelAndView("list-printers");
        model.addObject("temp", new Temp());
        model.addObject("username", userService.findUserByUsername().getFullname());
        model.addObject("printerList", printerService.findAll());
        return model;
    }
//    @GetMapping({"/list-printers"})
//    public ModelAndView getAllPrinters(Model model){
//        model.addAttribute("temp", new Temp());
//        model.addAttribute("username",  userService.findUserByUsername().getFullname());
//        model.addAttribute("printer", new Printer());
//        List<Department> departmentList = departmentService.findAll();
//        List<Toner> tonerList=tonerService.findAll();
////        List<Printer> printerList=printerService.findAll();
////        model.addAttribute("printers", printerList);
//        model.addAttribute("toners", tonerList);
//        model.addAttribute("departments", departmentList);
//        return "list-printers";
//    }

    @GetMapping({"/showInfoForm"})
    public ModelAndView showInfoForm(@RequestParam long id){
        ModelAndView model = new ModelAndView("info-printer-form");
        model.addObject("username", userService.findUserByUsername().getFullname());
        model.addObject("counter", printerService.getActualCounter(id));
        model.addObject("printer", printerRepo.findById(id).get().getManufacturer()+" "+ printerRepo.findById(id).get().getModel()+" w dziale "+ printerRepo.findById(id).get().getDepartment().getNameOfDepartment());
//        model.addObject("tonerBlack", printerService.getActualTonerLevel(id,"KMBlackTonerLevel"));
//        model.addObject("tonerCyan", printerService.getActualTonerLevel(id,"KMCyanTonerLevel"));
//        model.addObject("tonerMagenta", printerService.getActualTonerLevel(id,"KMMagentaTonerLevel"));
//        model.addObject("tonerYellow", printerService.getActualTonerLevel(id,"KMYellowTonerLevel"));
        return model;
    }

    @GetMapping("/addPrinterForm")
    public ModelAndView addPrinterForm() {
        ModelAndView model = new ModelAndView("add-printers-form");
        Printer printer=new Printer();
        model.addObject("printer", printer);
        List<Department> departmentList = departmentService.findAll();
        List<Toner> tonerList = tonerService.findAll();
        model.addObject("toners", tonerList);
        model.addObject("departments", departmentList);
        return model;

    }

    @PostMapping({"/savePrinter"})
    public String savePrinter(@ModelAttribute Printer printer) {
        printerService.save(printer);
        return "redirect:/list-printers";
    }

//    @PostMapping({"/deletePrinter"})
//    public String deletePrinter(Printer printer, Model model){
//        printerPage(model);
//        printerService.delete(printer);
//        model.addAttribute("info", "Usunąłeś drukarkę "+ printer.getModel());
//        return "/printer";
//    }

    //      INNA METODA
    @GetMapping({"/deletePrinter/{id}"})
    public String deletePrinter(@PathVariable(value = "id") long id) {
        printerService.deletePrinter(id);
        getAllPrinters();
        return "redirect:/list-printers";
    }



    @PostMapping({"/list-printers"})
    public void searchPrinters(@ModelAttribute("temp") Temp temp, Model model) {

        List<Printer> printerList = null;
        String mattern = '%' + temp.getTempString() + '%';

        if (temp.getTempString() == null) {
            if (printerList == null) {
                model.addAttribute("allert", "Brak danych!");
            }
            printerList = printerService.findAll();

        } else {
            if(printerService.findAllByModelIsLike(mattern).size()!=0){
                printerList = printerService.findAllByModelIsLike(mattern);

            }
            else if(printerRepo.findAllByManufacturerIsLike(mattern).size()!=0){
             printerList=printerRepo.findAllByManufacturerIsLike(mattern);
            }
//            else if(printerRepo.findAllByTonerIsLike(mattern).size()!=0){
//                printerList=printerRepo.findAllByTonerIsLike(mattern);
//            }
            else {
                printerList=printerRepo.findAllByIPAdressIsLike(mattern);
            }

        }
        model.addAttribute("printerList", printerList);
        model.addAttribute("username", userService.findUserByUsername().getFullname());
        getAllPrinters();

    }

    @GetMapping({"/showUpdateForm"})
    public ModelAndView showUpdateForm(@RequestParam Long printerId) {
        ModelAndView model = new ModelAndView("add-printers-form");
        Printer printer = printerRepo.findById(printerId).get();
        model.addObject("username", userService.findUserByUsername().getFullname());
        List<Department> departmentList = departmentService.findAll();
        List<Toner> tonerList = tonerService.findAll();
        model.addObject("printer", printer);
        model.addObject("oids", printerService.findActualUse(printerId));
        model.addObject("toners", tonerList);
        model.addObject("departments", departmentList);
        //model.addObject("oids", oidList);
        return model;
    }

//    @NotNull
//    private ModelAndView getModelAndView(ModelAndView model) {
//        model.addObject("username", userService.findUserByUsername().getFullname());
//        List<Department> departmentList = departmentService.findAll();
//        List<Toner> tonerList = tonerService.findAll();
//
//        model.addObject("toners", tonerList);
//        model.addObject("departments", departmentList);
//
//        return model;
//    }


//---------------------------END PRINTERS--------------------------------------------------


    @GetMapping({"/addPhone"})
    public String addPhonePage(Model model) {
        List<PhoneNumber> phoneNumbers = phoneNumberService.findAll();
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
