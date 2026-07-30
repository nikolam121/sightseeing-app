package hr.tis.academy.sightseeingapp.controller;

import hr.tis.academy.sightseeingapp.dto.NewAttractionFormDto;
import hr.tis.academy.sightseeingapp.enums.Type;
import hr.tis.academy.sightseeingapp.service.AttractionBrowseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AttractionViewController {

    private final AttractionBrowseService attractionBrowseService;

    public AttractionViewController(AttractionBrowseService attractionBrowseService) {
        this.attractionBrowseService = attractionBrowseService;
    }

    @GetMapping("/browse/attractions")
    public String listAttractions(Model model) {
        model.addAttribute("attractions", attractionBrowseService.getAllAttractionRows());
        return "attraction/list";
    }

    @GetMapping("/browse/attractions/new")
    public String showCreateForm(Model model) {
        model.addAttribute("attractionForm", new NewAttractionFormDto());
        model.addAttribute("types", Type.values());
        return "attraction/form";
    }

    @PostMapping("/browse/attractions/new")
    public String createAttraction(@Valid @ModelAttribute("attractionForm") NewAttractionFormDto attractionForm,
                                   BindingResult bindingResult,
                                   Model model) {
        model.addAttribute("types", Type.values());

        if (bindingResult.hasErrors()) {
            return "attraction/form";
        }

        attractionBrowseService.createAttraction(attractionForm);

        model.addAttribute("attractionForm", new NewAttractionFormDto());
        model.addAttribute("successMessage", "Atrakcija je uspješno unesena.");
        return "attraction/form";
    }
}