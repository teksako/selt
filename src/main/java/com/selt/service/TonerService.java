package com.selt.service;

import com.selt.model.Toner;
import com.selt.repository.TonerRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class TonerService {

    private final TonerRepo tonerRepo;

    public void save(Toner toner){
        tonerRepo.save(toner);
    }


}
