package com.selt.controler;

import com.selt.model.Department;
import com.selt.repository.DepartmentRepo;
import com.selt.service.DepartmentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Data
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping({"/addDepartment"})
    public String saveDepartment(Department department, Model model){
        model.addAttribute("department", new Department());
        departmentService.save(department);
        return "/index";
    }
}
