package com.selt.service;

import com.selt.model.Raport;
import com.selt.repository.RaportRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class RaportService {

    private final RaportRepo raportRepo;

    public List<Raport> findAll(){
        return raportRepo.findAll();
    }

    public void save(Raport raport){
        raportRepo.save(raport);
    }


}
