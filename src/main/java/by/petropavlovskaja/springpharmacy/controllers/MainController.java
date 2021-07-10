package by.petropavlovskaja.springpharmacy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

    @GetMapping
    public String showMainPage(Model model){
        return "index";
    }

/*    @GetMapping("/medicines")
    public String showMedicines(Model model){
        return "index";
    }*/

/*    @GetMapping("/login")
    public String newAccount(Model model){
        return "redirect:/accounts/login";
    }*/
}
