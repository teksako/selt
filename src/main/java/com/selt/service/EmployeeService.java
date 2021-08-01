package com.selt.service;

import com.selt.model.Employee;
import com.selt.repository.EmployeeRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepo employeeRepo;


    public void save(Employee employee) {
        employeeRepo.save(employee);
        //return "Dodano pracownika: " +employee;
    }
}
