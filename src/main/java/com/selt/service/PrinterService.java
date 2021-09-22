package com.selt.service;

import com.selt.model.Printer;
import com.selt.repository.PrinterRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class PrinterService {


    private final PrinterRepo printerRepo;

    public List<Printer> findAll(){
        return printerRepo.findAll();
    }

    public void save(Printer printer) {
        printerRepo.save(printer);
    }

    public void delete(Printer printer) {
        printerRepo.delete(printer);
    }

    public List<Printer> findAllByToner(String toner){
        return printerRepo.findAllByTonerIs(toner);
    }
}
