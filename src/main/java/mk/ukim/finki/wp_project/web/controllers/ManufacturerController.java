package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;
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
        model.addAttribute("bodyContent", "manufacturer_browse");
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
        model.addAttribute("bodyContent", "manufacturer_form");
        return "main_view";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) throws InvalidManufacturerIdException {
        List<Country> countries = this.countryService.listAll();
        model.addAttribute("manufacturer", this.manufacturerService.findById(id));
        model.addAttribute("countries", countries);
        model.addAttribute("bodyContent", "manufacturer_form");
        return "main_view";
    }


    @PostMapping
    public String create(@RequestParam String name, @RequestParam Long countryId, @RequestParam String image, Model model) throws InvalidCountryIdException {
        this.manufacturerService.create(name,countryId, image);
        List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        model.addAttribute("bodyContent", "manufacturer_browse");
        model.addAttribute("manufacturers", manufacturers);
        return "main_view";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam Long countryId,
                         @RequestParam String image,
                         Model model) throws InvalidManufacturerIdException, InvalidCountryIdException {

        this.manufacturerService.update(id, name, countryId, image);
        model.addAttribute("bodyContent", "manufacturer");
        model.addAttribute("manufacturer", this.manufacturerService.findById(id));
        return "main_view";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) throws InvalidManufacturerIdException {
        this.manufacturerService.delete(id);
        List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        model.addAttribute("bodyContent", "manufacturer_browse");
        model.addAttribute("carModels", manufacturers);
        return "main_view";
    }
}
