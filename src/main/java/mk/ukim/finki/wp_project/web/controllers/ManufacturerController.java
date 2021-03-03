package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp_project.service.CountryService;
import mk.ukim.finki.wp_project.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;
    private final CountryService countryService;

    public ManufacturerController(ManufacturerService manufacturerService, CountryService countryService) {
        this.manufacturerService = manufacturerService;
        this.countryService = countryService;
    }

    @GetMapping()
    public String showManufacturers(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        model.addAttribute("bodyContent", "browse_manufacturer");
        model.addAttribute("manufacturers", manufacturers);
        return "main_view";
    }
    @GetMapping("/{id}")
    public String showCar(@PathVariable Long id, Model model) throws InvalidManufacturerIdException {
        model.addAttribute("manufacturer", this.manufacturerService.findById(id));
        model.addAttribute("bodyContent", "manufacturer");
        return "main_view";
    }

    @GetMapping("/add")
    public String showAddNewManufacturer(Model model){
        List<Country> countries = this.countryService.listAll();
        model.addAttribute("countries", countries);
        model.addAttribute("bodyContent", "new_manufacturer_form");
        return "main_view";
    }

    @PostMapping
    public String create(@RequestParam String name, @RequestParam Long countryId, Model model) throws InvalidCountryIdException {
        this.manufacturerService.create(name,countryId);
        List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        model.addAttribute("bodyContent", "browse_manufacturer");
        model.addAttribute("manufacturers", manufacturers);
        return "main_view";
    }
}
