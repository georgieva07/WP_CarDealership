package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("bodyContent", "country");
        model.addAttribute("countries", countries);
        return "main_view";
    }
    @GetMapping("/add")
    public String showAddNewCountry(Model model){
        model.addAttribute("bodyContent", "new_country_form");
        return "main_view";
    }

    @PostMapping
    public String create(@RequestParam String name, Model model) {
        this.countryService.create(name);
        List<Country> countries = this.countryService.listAll();
        model.addAttribute("bodyContent", "country");
        model.addAttribute("countries", countries);
        return "main_view";
    }
}
