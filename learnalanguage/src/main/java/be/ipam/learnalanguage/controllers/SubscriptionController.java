package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.services.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("subscriptions", subscriptionService.getAll());
        return "subscriptions";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("subscription", subscriptionService.getById(id));
        return "subscription-details";
    }
}

