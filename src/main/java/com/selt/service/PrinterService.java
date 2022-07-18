package com.selt.service;

import com.selt.model.*;
import com.selt.repository.OIDRepo;
import com.selt.repository.PrinterRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Data
@Service
@RequiredArgsConstructor
@EnableScheduling
@Transactional
public class PrinterService {


    private final PrinterRepo printerRepo;
    private final OIDRepo oidRepo;

    public Long getTonerId(Long printerId) {

        return printerRepo.findById(printerId).get().getToner().getId();
    }

    public Optional<Printer> findById(Long id){
        return printerRepo.findById(id);
    }

    public List<Printer> findAll() {
        List<Printer> list = printerRepo.findAll();
//        List<Printer> finalList = new ArrayList<>();
//        for (Printer printer : list) {
//            if(finalList.size()==0){
//                finalList.add(printer);
//            }
//            else {
//                for (Printer printer1 : finalList) {
//                    if (!printer.getManufacturer().equals(printer1.getManufacturer()) && !printer.getModel().equals(printer1.getModel())) {
//                        finalList.add(printer);
//                    }
//
//                }
//            }
//
//        }

        return list;
    }

    //@Scheduled(fixedDelay = 10000)

    public List<String> getIP() {
        List<Printer> printerList = printerRepo.findAll();
        List<String> IPAdress = new ArrayList<>();


        for (Printer printer : printerList) {
            String model = printer.getManufacturer();
            if (model.equals("Konica Minolta") && !printer.getIPAdress().equals("-")) {

                IPAdress.add(printer.getIPAdress());
            }

        }
        return IPAdress;
    }

    //@Scheduled(cron = "0 39 14 ? * TUE")
    //@Scheduled(fixedDelay = 1000000)
    public void getCounter() {
        String community = "public";
        String oidval = ".1.3.6.1.2.1.43.10.2.1.4.1.1";
        //String ip="192.168.13.100";
//        for (String ip : getIP()) {
//            System.out.println(SNMP4J.snmpGet(ip, community, oidval));
//        }

        //System.out.println(getIP());
    }

    public List<Toner> findAlltoner(long id){
        Optional<Printer> printer=printerRepo.findById(id);
        List<Toner> tonerList=printer.get().getTonerList();
        return tonerList;
    }

    public List<OID> findActualUse(long id) {
        Optional<Printer> printer = printerRepo.findById(id);
        List<OID> actualList = printer.get().getOid();
        List<OID> findAll = oidRepo.findAll();
        List<OID> finallyList = new ArrayList<>();
        for (OID list : findAll) {
            if (actualList.size() != 0) {
                for (OID list2 : actualList) {
                    if (list.equals(list2)) {
                        finallyList.add(list);
                    }
                }
                //finallyList.remove(list);
            } else {
                finallyList = findAll;
            }

        }
        return findAll;
    }

//    public String getHPTonerLevel(){
//
//    }

    public List<String> getActualCounter(long id) {

        Optional<Printer> printer = printerRepo.findById(id);
        List<OID> oidList = printer.get().getOid();
        List<String> countList = new ArrayList<>();
        String community = "public";
        String oidName;
        if (printer.get().getIPAdress().equals("-")) {
            countList.add("Drukarka nie podłączona do sieci!");
        } else {
            for (OID oid : oidList) {
                oidName = oidRepo.findOIDById(oid.getId()).getOidName();

                countList.add(SNMP4J.snmpGet(printer.get().getIPAdress(), community, oid.getOidValue(), oidName));
            }
        }
        return countList;
    }

//    public String getActualTonerLevel(long id, String oidName){
//        Optional<Printer> printer = printerRepo.findById(id);
//        //Optional<OID> oid= oidRepo.findAllByOidName(oidName);
//
//        String community = "public";
////        String oidBlack =".1.3.6.1.2.1.43.11.1.1.9.1.4";
////        String oidCyan =".1.3.6.1.2.1.43.11.1.1.9.1.3";
////        String oidMagenta=".1.3.6.1.2.1.43.11.1.1.9.1.2";
////        String oidYellow=".1.3.6.1.2.1.43.11.1.1.9.1.1";
//        if(printer.get().getIPAdress().equals("-")){
//            return "Drukarka nie podłączona do sieci!";
//        }
//        else {
//            return SNMP4J.snmpGet(printer.get().getIPAdress(), community, oid.get()) + "%";
//        }
//    }


    public void save(Printer printer) {
        Toner toner = new Toner();
        toner.setId(1l);
        toner.setTonerName("testowy");
        printer.setToner(toner);
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
