package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.services.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", appUserService.getAll());
        return "users";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("user", appUserService.getById(id));
        return "user-details";
    }
}

