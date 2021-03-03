package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.Car;
import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;
import mk.ukim.finki.wp_project.service.CarModelService;
import mk.ukim.finki.wp_project.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;
    private final CarModelService carModelService;

    public CarController(CarService carService, CarModelService carModelService) {
        this.carService = carService;
        this.carModelService = carModelService;
    }
    @GetMapping("/browse")
    public String showBrowse(Model model) {
        List<Car> carList = this.carService.listAll();
        model.addAttribute("bodyContent", "browse");
        model.addAttribute("cars", carList);
        return "main_view";
    }

    @GetMapping("/car/{id}")
    public String showCar(@PathVariable Long id, Model model) {
        model.addAttribute("bodyContent", "car");
        model.addAttribute("carImage", "https://images2.minutemediacdn.com/image/upload/c_crop,h_726,w_1292,x_199,y_0/f_auto,q_auto,w_1100/v1578352479/shape/mentalfloss/62455-shout-factory1.jpg");
        model.addAttribute("carModelName", "car "+id.toString());
        model.addAttribute("carManufacturer", "manufacturer - pepsi");
        model.addAttribute("carDoors", 4);
        model.addAttribute("carEngine", "v90000 monster engine");
        model.addAttribute("carBody", "thicc curvatious body");
        model.addAttribute("carTurbo", "turbo? yes");
        model.addAttribute("carColor", "red");
        model.addAttribute("carId", id.toString());
        return "main_view";
    }


    @GetMapping("/car/compare/{id}")
    public String showCarCompare(@PathVariable Long id, Model model) {
        model.addAttribute("bodyContent", "car_compare");
        model.addAttribute("carImage", "https://images2.minutemediacdn.com/image/upload/c_crop,h_726,w_1292,x_199,y_0/f_auto,q_auto,w_1100/v1578352479/shape/mentalfloss/62455-shout-factory1.jpg");
        model.addAttribute("carModelName", "car "+id.toString());
        model.addAttribute("carManufacturer", "manufacturer - pepsi");
        model.addAttribute("carDoors", 4);
        model.addAttribute("carEngine", "v90000 monster engine");
        model.addAttribute("carBody", "thicc curvatious body");
        model.addAttribute("carTurbo", "turbo? yes");
        model.addAttribute("carColor", "red");
        model.addAttribute("carId", id.toString());
        return "main_view";
    }

    @GetMapping("/car/add")
    public String showAddNewCar(Model model){
        List<CarModel> carModels = this.carModelService.listAll();
        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyContent", "new_car_form");
        return "main_view";
    }

    @PostMapping("/browse")
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
        model.addAttribute("bodyContent", "browse");

        return "main_view";
    }
}
