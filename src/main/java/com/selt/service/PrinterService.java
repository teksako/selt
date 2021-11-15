package com.selt.service;

import com.selt.config.SNMP4Jcopy;
import com.selt.model.Printer;
import com.selt.repository.PrinterRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
@EnableScheduling
@Transactional
public class PrinterService {


    private final PrinterRepo printerRepo;

    public Long getTonerId(Long printerId) {

        return printerRepo.findById(printerId).get().getToner().getId();
    }

    public List<Printer> findAll() {
        return printerRepo.findAll();
    }

    //@Scheduled(fixedDelay = 10000)

    public List<String> getIP() {
        List<Printer> printerList = printerRepo.findAll();
        List<String> IPAdress = new ArrayList<>();

        //Printer printer=new Printer();
        for (Printer printer : printerList) {
            String model=printer.getManufacturer();
            if (model.equals("Konica Minolta") && !printer.getIPAdress().equals("-")) {
                //System.out.println(IP);
                IPAdress.add(printer.getIPAdress());
            }
            //System.out.println(printer.toString());
        }
        return IPAdress;
    }

    //@Scheduled(cron = "0 39 14 ? * TUE")
    @Scheduled(fixedDelay = 10000)
    public void getCounter() {
        String community = "public";
        String oidval = ".1.3.6.1.2.1.43.10.2.1.4.1.1";
        //String ip="192.168.13.100";
        for (String ip : getIP()) {
            System.out.println(SNMP4J.snmpGet(ip, community, oidval));
        }

        //System.out.println(getIP());
    }

    public String getActualCounter(long id){
        Optional<Printer> printer = printerRepo.findById(id);
        String community = "public";
        String oidval = ".1.3.6.1.2.1.43.10.2.1.4.1.1";
        if(printer.get().getIPAdress().equals("-")){
            return "Drukarka nie podłączona do sieci!";
        }
        else {
            return SNMP4J.snmpGet(printer.get().getIPAdress(), community, oidval);
        }
    }

    public String getActualTonerLevel(long id){
        Optional<Printer> printer = printerRepo.findById(id);
        String community = "public";
        String oidBlack =".1.3.6.1.2.1.43.12.1.1.4.1.4";
        String oidCyan =".1.3.6.1.2.1.43.12.1.1.4.1.1";
        String oidMagenta=".1.3.6.1.2.1.43.12.1.1.4.1.2";
        String oidYellow=".1.3.6.1.2.1.43.12.1.1.4.1.3";
        if(printer.get().getIPAdress().equals("-")){
            return "Drukarka nie podłączona do sieci!";
        }
        else {
            return SNMP4J.snmpGet(printer.get().getIPAdress(), community, oidBlack);
        }
    }


    public void test() {
        System.out.println("test2");
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

    public List<Printer> findAllByModelIsLike(String model) {
        return printerRepo.findAllByModelIsLike(model);
    }

    public List<Printer> findAllByToner(String toner) {
        return printerRepo.findAllByTonerIs(toner);
    }

//    public Printer findById(Long id){
//        return printerRepo.findById(id);
//    }
}
