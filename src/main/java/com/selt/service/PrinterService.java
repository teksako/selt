package com.selt.service;

import com.selt.config.SNMP4J;
import com.selt.model.Printer;
import com.selt.repository.PrinterRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<String> getIP(){
        List<Printer> printerList = printerRepo.findAll();
        List<String>IPAdress = new ArrayList<String>();
        for (Printer printer : printerList){
            if(printer.getModel()=="C454" || printer.getModel()=="C284" ) {
                IPAdress.add(printer.getIPAdress());
            }
        }
    return IPAdress;
    }

    public void getCounter(){

        String community = "public";
        String oidval = ".1.3.6.1.2.1.43.10.2.1.4.1.1";
        for (String ip: getIP()) {
            SNMP4J.snmpGet(ip,community,oidval);
        }

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
