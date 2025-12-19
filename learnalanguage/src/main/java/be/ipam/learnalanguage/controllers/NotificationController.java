package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.services.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("notifications", notificationService.getAll());
        return "notifications";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("notification", notificationService.getById(id));
        return "notification-details";
    }
}
