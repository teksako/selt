package com.selt.service;

import com.selt.model.Raport;
import com.selt.model.Temp;
import com.selt.repository.RaportRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Data
public class RaportService {

    private final RaportRepo raportRepo;
    private LocalDate date;
    private final TempService tempService;


    public List<Raport> findAll() {
        return raportRepo.findAll();
    }

    public void save(Raport raport) {
        raport.setDate(LocalDate.now());

        raportRepo.save(raport);
    }

    public List<Raport> findAllByActualMonth() {
        LocalDate start;
        LocalDate end;
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        start = LocalDate.of(year, month, 1);
        int day;

        if (year % 4 == 0) {

            if (month == 2) {
                end = LocalDate.of(year, month, 29);
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                end = LocalDate.of(year, month, 31);
            } else {
                end = LocalDate.of(year, month, 30);
            }
        } else {
            if (month == 2) {
                end = LocalDate.of(year, month, 28);
            } else {
                end = LocalDate.of(year, month, 30);
            }

        }
        return raportRepo.findAllByDateIsBetween(start, end);

    }

    public List<Raport> search(String temp) {
        return raportRepo.findAllByPrinters_ModelIsLike(temp);
    }

    public List<Raport> findAllByDateBetween(LocalDate start, LocalDate end) {
        return raportRepo.findAllByDateIsBetween(start, end);

    }

    public List<Raport> findAllByPrinters_Toner_TonerNameIsLike(String temp) {
        return raportRepo.findAllByPrinters_Toner_TonerNameIsLike(temp);
    }

    public List<Raport> findAllByPrinters_Department_NameOfDepartmentIsLike(String temp) {
        return raportRepo.findAllByPrinters_Department_NameOfDepartmentIsLike(temp);
    }

    public List<Raport> findAllByPrinters_Toner_TonerNameIsLike(String temp){
        return raportRepo.findAllByPrinters_Toner_TonerNameIsLike(temp);
    }

    public List<Raport> findAllByPrinters_Department_NameOfDepartmentIsLike(String temp){
        return raportRepo.findAllByPrinters_Department_NameOfDepartmentIsLike(temp);
    }
}

