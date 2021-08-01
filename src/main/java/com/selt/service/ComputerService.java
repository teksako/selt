package com.selt.service;

import com.selt.model.Computer;
import com.selt.repository.ComputerRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class ComputerService {

    @Autowired
    private final ComputerRepo computerRepo;

    public void save(Computer computer){
        computerRepo.save(computer);
    }
}
