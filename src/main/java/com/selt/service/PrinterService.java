package com.selt.service;

import com.selt.model.Printer;
import com.selt.repository.PrinterRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class PrinterService {


    private final PrinterRepo printerRepo;

    public void save(Printer printer) {
        printerRepo.save(printer);
    }

    public void delete(Printer printer) {
        printerRepo.delete(printer);
    }
}
