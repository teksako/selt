package com.selt.repository;

import com.selt.model.Raport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RaportRepo extends JpaRepository<Raport, Long> {
    List<Raport> findAllByDateIsBetween(LocalDate start, LocalDate end);
    List<Raport> findAllByPrinters_ModelIsLike(String search);
    List<Raport> findAllByPrinters_Toner_TonerNameIsLike(String search);
    List<Raport> findAllByPrinters_Department_NameOfDepartmentIsLike(String search);


}
