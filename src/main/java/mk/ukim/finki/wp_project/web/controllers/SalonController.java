package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.Salon;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.service.CountryService;
import mk.ukim.finki.wp_project.service.SalonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dealership")
public class SalonController {
    private final SalonService salonService;
    private final CountryService countryService;

    public SalonController(SalonService salonService, CountryService countryService) {
        this.salonService = salonService;
        this.countryService = countryService;
    }

    @GetMapping
    public String showSalon(Model model) {
        List<Salon> salons = this.salonService.listAll();
        model.addAttribute("bodyContent", "salon");
        model.addAttribute("salons", salons);
        return "main_view";
    }
    @GetMapping("/add")
    public String showAddNewSalon(Model model){
        model.addAttribute("bodyContent", "new_salon_form");
        model.addAttribute("countries", this.countryService.listAll());
        return "main_view";
    }

    @PostMapping
    public String create(@RequestParam String address, @RequestParam Long countryId, Model model) throws InvalidCountryIdException {
        this.salonService.create(address, countryId);
        List<Salon> salons = this.salonService.listAll();
        model.addAttribute("bodyContent", "salon");
        model.addAttribute("salons", salons);
        return "main_view";
    }
}
