package com.selt.service;

import com.selt.model.Office;
import com.selt.repository.OfficeRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class OfficeService {

    @Autowired
    private OfficeRepo officeRepo;

    public void save(Office office){
        officeRepo.save(office);
    }
}
