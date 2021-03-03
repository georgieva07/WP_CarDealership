package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.Salon;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidSalonIdException;
import mk.ukim.finki.wp_project.service.CountryService;
import mk.ukim.finki.wp_project.service.SalonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("bodyContent", "browse_salon");
        model.addAttribute("salons", salons);
        return "main_view";
    }

    @GetMapping("/{id}")
    public String showSalon(@PathVariable Long id, Model model) throws InvalidSalonIdException {
        model.addAttribute("bodyContent", "salon");
        model.addAttribute("salon", this.salonService.findById(id));
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
    public String create(@RequestParam String address, @RequestParam String city, @RequestParam Long countryId, Model model) throws InvalidCountryIdException {
        this.salonService.create(address, city, countryId);
        List<Salon> salons = this.salonService.listAll();
        model.addAttribute("bodyContent", "browse_salon");
        model.addAttribute("salons", salons);
        return "main_view";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String address,
                         @RequestParam String city,
                         @RequestParam Long countryId,
                         Model model) throws InvalidCountryIdException, InvalidSalonIdException {

        this.salonService.update(id, address, city, countryId);
        List<Salon> salons = this.salonService.listAll();
        model.addAttribute("bodyContent", "browse_salon");
        model.addAttribute("salons", salons);
        return "main_view";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) throws InvalidSalonIdException {
        this.salonService.delete(id);
        List<Salon> salons = this.salonService.listAll();
        model.addAttribute("bodyContent", "browse_salon");
        model.addAttribute("salons", salons);
        return "main_view";
    }

}
