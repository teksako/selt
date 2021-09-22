package com.selt.repository;

import com.selt.model.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrinterRepo extends JpaRepository<Printer, Long> {

    public List<Printer> findAllByTonerIs(String toner);
}
