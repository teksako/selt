package com.selt.service;

import com.selt.model.Printer;
import com.selt.repository.PrinterRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class PrinterService {


    private final PrinterRepo printerRepo;

    public Long getTonerId(Long printerId){

        return printerRepo.findById(printerId).get().getToner().getId();
    }

    public List<Printer> findAll(){
        return printerRepo.findAll();
    }

    public void save(Printer printer) {
        printerRepo.save(printer);
    }

//    public void delete(Printer printer) {
//        printerRepo.delete(printer);
//    }

    public void deletePrinter(long id) {
        Optional<Printer> printer1 = printerRepo.findById(id);
        printerRepo.delete(printer1.get());
    }

    public void editPrinter(long id) {
        Optional<Printer> printer1 = printerRepo.findById(id);
        printerRepo.save(printer1.get());
    }

    public List<Printer> findAllByModelIsLike(String model){
        return printerRepo.findAllByModelIsLike(model);
    }

    public List<Printer> findAllByToner(String toner){
        return printerRepo.findAllByTonerIs(toner);
    }

//    public Printer findById(Long id){
//        return printerRepo.findById(id);
//    }
}
