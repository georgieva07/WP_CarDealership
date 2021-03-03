package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.service.CarModelService;
import mk.ukim.finki.wp_project.service.ManufacturerService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarModelController {
    private final CarModelService carModelService;
    private final ManufacturerService manufacturerService;

    public CarModelController(CarModelService carModelService, ManufacturerService manufacturerService) {
        this.carModelService = carModelService;
        this.manufacturerService = manufacturerService;
    }

}
