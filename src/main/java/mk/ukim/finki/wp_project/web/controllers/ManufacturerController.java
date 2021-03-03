package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.service.CountryService;
import mk.ukim.finki.wp_project.service.ManufacturerService;
import org.springframework.stereotype.Controller;

@Controller
public class ManufacturerController {
    private final ManufacturerService manufacturerService;
    private final CountryService countryService;

    public ManufacturerController(ManufacturerService manufacturerService, CountryService countryService) {
        this.manufacturerService = manufacturerService;
        this.countryService = countryService;
    }
}
