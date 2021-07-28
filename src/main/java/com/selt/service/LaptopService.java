package com.selt.service;

import com.selt.model.Laptop;
import com.selt.repository.LaptopRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class LaptopService {

    @Autowired
    private final LaptopRepo laptopRepo;

    public void save(Laptop laptop){
        laptopRepo.save(laptop);
    }


}
