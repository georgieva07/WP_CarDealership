package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.Car;
import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidSalonIdException;
import mk.ukim.finki.wp_project.service.CarModelService;
import mk.ukim.finki.wp_project.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/model")
public class CarModelController {
    private final CarModelService carModelService;
    private final ManufacturerService manufacturerService;

    public CarModelController(CarModelService carModelService, ManufacturerService manufacturerService) {
        this.carModelService = carModelService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String showModels(Model model) {
        List<CarModel> carModels = this.carModelService.listAll();
        model.addAttribute("bodyContent", "car_model_browse");
        model.addAttribute("carModels", carModels);
        return "main_view";
    }

    @GetMapping("/{id}")
    public String showCar(@PathVariable Long id, Model model) throws InvalidCarModelIdException {
        model.addAttribute("carModel", this.carModelService.findById(id));
        model.addAttribute("bodyContent", "car_model");
        return "main_view";
    }

    @GetMapping("/add")
    public String showAddNewCarModel(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("bodyContent", "car_model_form");
        return "main_view";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) throws InvalidCarModelIdException {
        CarModel carModel = this.carModelService.findById(id);
        List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("carModel", carModel);
        model.addAttribute("bodyContent", "car_model_form");
        return "main_view";
    }

    @PostMapping
    public String create(@RequestParam Long manufacturerId,
                         @RequestParam String name,
                         @RequestParam String image,
                         Model model) throws InvalidManufacturerIdException {
        this.carModelService.create(name, manufacturerId, image);
        List<CarModel> carModels = this.carModelService.listAll();
        model.addAttribute("bodyContent", "car_model_browse");
        model.addAttribute("carModels", carModels);
        return "main_view";
    }
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam Long manufacturerId,
                         @RequestParam String name,
                         @RequestParam String image,
                         Model model) throws InvalidCarModelIdException, InvalidManufacturerIdException {

        this.carModelService.update(id, manufacturerId, name, image);
        model.addAttribute("bodyContent", "car_model");
        model.addAttribute("carModel", this.carModelService.findById(id));
        return "main_view";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) throws InvalidCarModelIdException {
        this.carModelService.delete(id);
        List<CarModel> carModels = this.carModelService.listAll();
        model.addAttribute("bodyContent", "car_model_browse");
        model.addAttribute("carModels", carModels);
        return "main_view";
    }
}
