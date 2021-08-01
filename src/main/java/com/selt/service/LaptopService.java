package com.selt.service;

import com.selt.model.Laptop;
import com.selt.repository.LaptopRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class LaptopService {


    private final LaptopRepo laptopRepo;

    public void save(Laptop laptop){
        laptopRepo.save(laptop);
    }

    public List<Laptop> findAl(){
        return laptopRepo.findAll();
    }


}
