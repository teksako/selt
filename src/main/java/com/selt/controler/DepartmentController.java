package com.selt.controler;

import com.selt.model.Department;
import com.selt.model.Location;
import com.selt.repository.DepartmentRepo;
import com.selt.service.DepartmentService;
import com.selt.service.LocationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Data
public class DepartmentController {

    private final DepartmentService departmentService;
    private final LocationService locationService;

    @GetMapping({"/addDepartment"})
    public String departmentPage(Model model){
        model.addAttribute("department", new Department());
        List<Location> locationList = locationService.findAll();
        model.addAttribute("locationSList", locationList);
        return "/addDepartment";
    }

    @PostMapping({"/addDepartment"})
    public String saveDepartment(Department department){
        departmentService.save(department);
        return "/index";
    }
}
