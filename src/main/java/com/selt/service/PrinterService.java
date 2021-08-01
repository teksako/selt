package com.selt.service;

import com.selt.repository.PrinterRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class PrinterService {


    private final PrinterRepo printerRepo;
}
