package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.*;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidSalonIdException;
import mk.ukim.finki.wp_project.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/dealership")
public class SalonController {
    private final SalonService salonService;
    private final CarService carService;
    private final CarInStockService carInStockService;
    private final CountryService countryService;
    private final UserService userService;

    public SalonController(SalonService salonService, CarService carService, CarInStockService carInStockService, CountryService countryService, UserService userService) {
        this.salonService = salonService;
        this.carService = carService;
        this.carInStockService = carInStockService;
        this.countryService = countryService;
        this.userService = userService;
    }


    @GetMapping
    public String showSalon(Model model) {
        List<Salon> salons = this.salonService.listAll();
        model.addAttribute("bodyContent", "salon_browse");
        model.addAttribute("salons", salons);
        return "main_view";
    }

    @GetMapping("/{id}")
    public String showSalon(@PathVariable Long id, Model model, HttpServletRequest req) throws InvalidSalonIdException {
        String username = req.getRemoteUser();
        User user = userService.findByUsername(username);
        model.addAttribute("bodyContent", "salon");
        model.addAttribute("salon", this.salonService.findById(id));
        model.addAttribute("user", user);
        model.addAttribute("cars", this.salonService.findById(id).getCarsInStock());

        return "main_view";
    }

    @GetMapping("/add")
    public String showAddNewSalon(Model model){
        model.addAttribute("bodyContent", "salon_form");
        model.addAttribute("countries", this.countryService.listAll());
        return "main_view";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) throws InvalidSalonIdException {
        Salon salon = this.salonService.findById(id);
        List<Country> countries = this.countryService.listAll();
        model.addAttribute("salon", salon);
        model.addAttribute("countries", countries);
        model.addAttribute("bodyContent", "salon_form");
        return "main_view";
    }

    @PostMapping
    public String create(@RequestParam String address,
                         @RequestParam String city,
                         @RequestParam Long countryId,
                         Model model, HttpServletRequest req) throws InvalidCountryIdException {
        User user = this.userService.findByUsername(req.getRemoteUser());
        Salon salon = this.salonService.create(address, city, countryId, user);
        List<Salon> salons = this.salonService.listAll();
        model.addAttribute("bodyContent", "salon_browse");
        model.addAttribute("salons", salons);
        return "main_view";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String address,
                         @RequestParam String city,
                         @RequestParam Long countryId,
                         Model model,
                         HttpServletRequest req) throws InvalidCountryIdException, InvalidSalonIdException {
        User user = this.userService.findByUsername(req.getRemoteUser());
        this.salonService.update(id, address, city, countryId, user);
        List<Salon> salons = this.salonService.listAll();
        model.addAttribute("bodyContent", "salon_browse");
        model.addAttribute("salons", salons);
        return "main_view";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) throws InvalidSalonIdException {
        this.salonService.delete(id);
        List<Salon> salons = this.salonService.listAll();
        model.addAttribute("bodyContent", "salon_browse");
        model.addAttribute("salons", salons);
        return "main_view";
    }

    @GetMapping("/{id}/add/carstock")
    public String showAddCarInStock(@PathVariable Long id, Model model) throws InvalidSalonIdException {
        List<Car> cars = this.carService.listAll();
        model.addAttribute("bodyContent", "car_in_stock_form");
        model.addAttribute("salon", this.salonService.findById(id));
        model.addAttribute("cars", cars);
        return "main_view";
    }

    @PostMapping("/{id}/add/carstock")
    public String addCarInStock(@PathVariable Long id, @RequestParam Long carId, @RequestParam Integer quantity, Model model) throws InvalidSalonIdException, InvalidCarIdException {
        this.carInStockService.create(carId, id, quantity);
        model.addAttribute("bodyContent", "salon_browse");
        model.addAttribute("salons", this.salonService.listAll());
        return "main_view";
    }

}
