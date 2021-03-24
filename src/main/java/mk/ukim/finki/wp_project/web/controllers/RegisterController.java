package mk.ukim.finki.wp_project.web.controllers;

import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp_project.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp_project.service.CountryService;
import mk.ukim.finki.wp_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final CountryService countryService;


    public RegisterController(UserService userService, CountryService countryService) {
        this.userService = userService;
        this.countryService = countryService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Country> countries = this.countryService.listAll();
        model.addAttribute("bodyContent","register");
        model.addAttribute("countries", countries);
        return "main_view";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String country){
        try {
            this.userService.register(username, password, repeatPassword, name, surname, country);
            return "redirect:/login";
        }catch (PasswordsDoNotMatchException | InvalidArgumentsException ex){
            return "redirect:/register?error=" + ex.getMessage();
        }
    }
}
