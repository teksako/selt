package com.selt.controler;


import com.selt.model.Location;
import com.selt.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping({"/addLocation"})
    public String locationPage(Model model){
        model.addAttribute("location", new Location());
        return "/addLocation";
    }

    @PostMapping({"/addLocation"})
    public String saveLocation(Location location){
        locationService.save(location);
        return "/addLocation";
    }
}
