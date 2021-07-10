package by.petropavlovskaja.springpharmacy.controllers;

import by.petropavlovskaja.springpharmacy.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

    List<String> accountList = new ArrayList<>();

    @GetMapping("/login")
    public String index(Model model) {
//        model.addAttribute("a", new Account());
        return "accounts/login";
    }

    @GetMapping("/signup")
    public String newAccount(Model model) {
//        model.addAttribute("a", new Account());
        return "accounts/new";
    }

    @GetMapping("/logout")
    public String exit(Model model) {
//        model.addAttribute("a", new Account());
        return "accounts/logout";
    }

}
