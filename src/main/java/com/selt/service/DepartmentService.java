package com.selt.service;

import com.selt.model.Department;
import com.selt.repository.DepartmentRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepo departmentRepo;

    public void save(Department department){
        departmentRepo.save(department);
    }
}
