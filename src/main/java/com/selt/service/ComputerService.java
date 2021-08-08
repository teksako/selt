package com.selt.service;

import com.selt.model.Computer;
import com.selt.repository.ComputerRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ComputerService {

    @Autowired
    private final ComputerRepo computerRepo;

    public List<Computer> findAll(){
        return computerRepo.findAll();
    }

    public List<Computer>findAllByEmployee(){
        return computerRepo.findAllByEmployee_LastnameIs("Sobolewski");
    }

    public void save(Computer computer){
        computerRepo.save(computer);
    }
}
