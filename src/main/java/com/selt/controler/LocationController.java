package com.selt.controler;


import com.selt.model.Location;
import com.selt.repository.LocationRepo;
import com.selt.service.LocationService;
import com.selt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final UserService userService;
    private final LocationRepo locationRepo;


    @GetMapping({"/list-locations"})
    public ModelAndView getAllLocation() {
        ModelAndView model = new ModelAndView("/list-locations");
        model.addObject("username", userService.findUserByUsername().getFullname());
        model.addObject("locationList", locationService.findAll());
        return model;
    }


//    @GetMapping({"/addLocation"})
//    public String locationPage(Model model){
//        model.addAttribute("location", new Location());
//        return "list-locations";
//    }

    @PostMapping({"/saveLocation"})
    public String saveLocation(@ModelAttribute Location location) {
        locationService.save(location);
        return "redirect:list-locations";
    }

    @GetMapping({"/addLocationForm"})
    public ModelAndView addLocationForm() {
        ModelAndView model = new ModelAndView("add-location-form");
        Location location = new Location();
        model.addObject("location", location);
        return getModelAndView(model);
    }

    @GetMapping({"/showUpdateLocationForm"})
    public ModelAndView showUpdateLocationForm(@RequestParam Long locationId) {
        ModelAndView model = new ModelAndView("add-location-form");
        Location location = locationRepo.findById(locationId).get();
        model.addObject("location", location);
        return getModelAndView(model);
    }

    @NotNull
    private ModelAndView getModelAndView(ModelAndView model) {
        model.addObject("username", userService.findUserByUsername().getFullname());
//        List<Location> locationList = locationService.findAll();
//        model.addObject("locations", locationList);
        return model;
    }

    @GetMapping({"/deleteLocation/{id}"})
    public String deleteLocation(@PathVariable(value = "id") long id) {
        locationService.deleteLocation(id);
        getAllLocation();
        return "redirect:/list-departments";
    }
}


