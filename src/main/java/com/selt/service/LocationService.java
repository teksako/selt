package com.selt.service;

import com.selt.repository.LocationRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
public class LocationService {

    private final LocationRepo locationRepo;




}
