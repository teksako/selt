package com.selt.service;

import com.selt.model.Windows;
import com.selt.repository.WindowsRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class WindowsService {
    @Autowired
    private final WindowsRepo windowsRepo;

    public void save(Windows windows){
        windowsRepo.save(windows);
    }
}
