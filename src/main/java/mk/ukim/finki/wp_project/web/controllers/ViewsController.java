package mk.ukim.finki.wp_project.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class ViewsController {

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "home");
        return "main_view";
    }

    @GetMapping("/Car/{id}")
    public String getCarPage(@PathVariable Long id, Model model) {
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

    @GetMapping("/CarCompare/{id}")
    public String getCarComparePage(@PathVariable Long id, Model model) {
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

    @GetMapping("/Browse")
    public String getBrowsePage(Model model) {
        model.addAttribute("bodyContent", "browse");
        return "main_view";
    }
}
