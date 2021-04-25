package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.Salon;
import mk.ukim.finki.wp_project.model.User;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidSalonIdException;
import mk.ukim.finki.wp_project.service.CountryService;
import mk.ukim.finki.wp_project.service.SalonService;
import mk.ukim.finki.wp_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/dealership")
public class SalonController {
    private final SalonService salonService;
    private final CountryService countryService;
    private final UserService userService;

    public SalonController(SalonService salonService, CountryService countryService, UserService userService) {
        this.salonService = salonService;
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
        model.addAttribute("bodyContent", "salon_browse");
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

}
