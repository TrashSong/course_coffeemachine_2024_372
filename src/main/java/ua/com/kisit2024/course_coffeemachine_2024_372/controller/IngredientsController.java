package ua.com.kisit2024.course_coffeemachine_2024_372.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.IngredientsService;

@Controller
public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/ingredients")
    public String getPageIngredients(Model model){
        model.addAttribute("ingredients_menu", ingredientsService.findAllIngredients());
        return "index2";
    }

}
