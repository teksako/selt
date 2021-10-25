package com.selt.service;

import com.selt.model.Department;
import com.selt.model.Printer;
import com.selt.repository.DepartmentRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepo departmentRepo;

    public void save(Department department){
        departmentRepo.save(department);
    }

    public void delete(Department department){ departmentRepo.save(department);}

    public List<Department> findAll(){
       return departmentRepo.findAll();
    }

    public void deleteDepartment(long id) {
        Optional<Department> department = departmentRepo.findById(id);
        departmentRepo.delete(department.get());
    }

    public List<Department> findAllByNameOfDepartmentIsLike(String name){
        return departmentRepo.findAllByNameOfDepartmentIsLike(name);
    }
}
