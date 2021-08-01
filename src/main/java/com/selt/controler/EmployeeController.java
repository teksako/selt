package com.selt.controler;

import com.selt.model.Employee;
import com.selt.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor

public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping({"/addEmployee"})
    public String addEmployee(Model model){

        model.addAttribute("employee", new Employee());
        return "/addEmployee";
    }

    @PostMapping({"/addEmployee"})
    public String saveEmployee(Employee employee){
//        if(bindingResult.hasErrors()){
//            return "/addEmployee";
//        }
        employeeService.save(employee);
        return "/addEmployee";
    }
}
