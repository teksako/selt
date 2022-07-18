package com.selt.service;

import com.selt.model.Counter;
import com.selt.model.Printer;
import com.selt.repository.CounterRepo;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Repository
public class CounterService {

    private final CounterRepo counterRepo;
    private final PrinterService printerService;

    public List<Printer> onlineList(){
        List<Printer> findAll=printerService.findAll();
        List<Printer> onlineList=new ArrayList<>();
        for (Printer printer:findAll) {
            if(printer.getIPAdress()!=null ||printer.getIPAdress()!="-"){
                onlineList.add(printer);
            }
        }
        return onlineList;
    }

    public void save(Counter counter){
        counterRepo.save(counter);
    }


}
