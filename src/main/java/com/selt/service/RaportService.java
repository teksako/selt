package com.selt.service;

import com.selt.model.Raport;
import com.selt.repository.RaportRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Data
public class RaportService {

    private final RaportRepo raportRepo;
    private LocalDate date;


    public List<Raport> findAll(){
        return raportRepo.findAll();
    }

    public void save(Raport raport){
        raport.setDate(LocalDate.now());

        raportRepo.save(raport);
    }


}
