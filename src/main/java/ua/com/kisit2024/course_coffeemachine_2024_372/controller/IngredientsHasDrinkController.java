package ua.com.kisit2024.course_coffeemachine_2024_372.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.IngredientsHasDrinkService;

public class IngredientsHasDrinkController {

    private final IngredientsHasDrinkService ingredientsHasDrinkService;

    public IngredientsHasDrinkController (IngredientsHasDrinkService ingredientsHasDrinkService) {
        this.ingredientsHasDrinkService = ingredientsHasDrinkService;
    }

//    @GetMapping("/")
//    public String getPageHome(Model model){
//        model.addAttribute("ingre_drink_menu", ingredientsHasDrinkService.findAllIngredientsHasDrink());
//        return "index2";
//    }
}
