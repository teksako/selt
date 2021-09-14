package com.selt.service;

import com.selt.model.Location;
import com.selt.repository.LocationRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class LocationService {

    private final LocationRepo locationRepo;

    public void save(Location location) {
        locationRepo.save(location);
    }

    public void delete(Location location) {
        locationRepo.save(location);
    }

    public List<Location> findAll() {
        return locationRepo.findAll();
    }

}
