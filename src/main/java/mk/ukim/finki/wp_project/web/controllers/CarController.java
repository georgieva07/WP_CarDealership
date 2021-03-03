package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.Car;
import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;
import mk.ukim.finki.wp_project.service.CarModelService;
import mk.ukim.finki.wp_project.service.CarService;
import mk.ukim.finki.wp_project.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final CarModelService carModelService;
    private final ManufacturerService manufacturerService;

    public CarController(CarService carService, CarModelService carModelService, ManufacturerService manufacturerService) {
        this.carService = carService;
        this.carModelService = carModelService;
        this.manufacturerService = manufacturerService;
    }
    @GetMapping
    public String showBrowse(Model model) {
        List<Car> carList = this.carService.listAll();
        model.addAttribute("bodyContent", "browse_car");
        model.addAttribute("cars", carList);
        return "main_view";
    }

    @GetMapping("/{id}")
    public String showCar(@PathVariable Long id, Model model) throws InvalidCarIdException {
        model.addAttribute("bodyContent", "car");
        model.addAttribute("car", this.carService.findById(id));
        return "main_view";
    }


    @GetMapping("/compare/{id}")
    public String showCarCompare(@PathVariable Long id, Model model) throws InvalidCarIdException {
        model.addAttribute("bodyContent", "car_compare");
        model.addAttribute("car", this.carService.findById(id));
        model.addAttribute("compare_with", this.carService.listAll());
        return "main_view";
    }

    @GetMapping("/add")
    public String showAddNewCar(Model model){
        List<CarModel> carModels = this.carModelService.listAll();
        List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        model.addAttribute("carModels", carModels);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("bodyContent", "new_car_form");
        return "main_view";
    }

    @PostMapping
    public String create(@RequestParam Long carModelId,
                         @RequestParam String body,
                         @RequestParam String engine,
                         @RequestParam String turbo,
                         @RequestParam Integer doors,
                         @RequestParam String color,
                         @RequestParam Double price,
                         @RequestParam String image,
                         Model model) throws InvalidCarModelIdException {
        this.carService.create(carModelId, body, engine, turbo, doors, color, price, image);
        List<Car> carList = this.carService.listAll();
        model.addAttribute("bodyContent", "browse_car");
        model.addAttribute("cars", carList);
        return "main_view";
    }
}
