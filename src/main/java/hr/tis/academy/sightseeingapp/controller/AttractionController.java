package hr.tis.academy.sightseeingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attractions")
public class AttractionController {
    @GetMapping("/{location}")
    public String listAttractions(@PathVariable("location") String location, Model model) {
        model.addAttribute("attractions", attractionService.findByLocation(location));
    }
}

