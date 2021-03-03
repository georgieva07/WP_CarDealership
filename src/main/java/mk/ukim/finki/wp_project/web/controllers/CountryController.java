package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.Salon;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidSalonIdException;
import mk.ukim.finki.wp_project.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String showCountry(Model model) {
        List<Country> countries = this.countryService.listAll();
        model.addAttribute("bodyContent", "country_browse");
        model.addAttribute("countries", countries);
        return "main_view";
    }

    @GetMapping("/{id}")
    public String showCountry(@PathVariable Long id, Model model) throws InvalidCountryIdException {
        model.addAttribute("bodyContent", "country");
        model.addAttribute("country", this.countryService.findById(id));
        return "main_view";
    }

    @GetMapping("/add")
    public String showAddNewCountry(Model model){
        model.addAttribute("bodyContent", "country_form");
        return "main_view";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) throws InvalidCountryIdException {
        Country country = this.countryService.findById(id);
        model.addAttribute("country", country);
        model.addAttribute("bodyContent", "country_form");
        return "main_view";
    }

    @PostMapping
    public String create(@RequestParam String name, Model model) {
        this.countryService.create(name);
        List<Country> countries = this.countryService.listAll();
        model.addAttribute("bodyContent", "country_browse");
        model.addAttribute("countries", countries);
        return "main_view";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         Model model) throws InvalidCountryIdException {

        this.countryService.update(id, name);
        List<Country> countries = this.countryService.listAll();
        model.addAttribute("bodyContent", "country_browse");
        model.addAttribute("countries", countries);
        return "main_view";
    }
}
