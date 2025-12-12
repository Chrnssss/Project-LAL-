package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.services.ExampleSentenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/examples")
public class ExampleSentenceController {

    private final ExampleSentenceService exampleSentenceService;

    public ExampleSentenceController(ExampleSentenceService exampleSentenceService) {
        this.exampleSentenceService = exampleSentenceService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("examples", exampleSentenceService.getAll());
        return "examples";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("example", exampleSentenceService.getById(id));
        return "example-details";
    }
}

