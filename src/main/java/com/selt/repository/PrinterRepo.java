package com.selt.repository;

import com.selt.model.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrinterRepo extends JpaRepository<Printer, Long> {

   List<Printer> findAllByTonerIs(String toner);
   List<Printer> findAllByModelIsLike(String model);
   List<Printer> findAllByManufacturerIsLike(String manufacturer);
   List<Printer> findAllByIPAdressIsLike(String ip);
   List<Printer> findAllByTonerIsLike(String toner);

}
