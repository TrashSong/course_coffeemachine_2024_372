package ua.com.kisit2024.course_coffeemachine_2024_372.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.DrinksService;

@Controller
public class DrinksController {

    private final DrinksService drinksService;

    public DrinksController(DrinksService drinksService) {
        this.drinksService = drinksService;
    }

    @GetMapping("/")
    public String getPageDrinks(Model model){
        model.addAttribute("drink_menu", drinksService.findAllDrinks());
        return "index2";
    }

    @GetMapping("/search")
    public String getPageSearch(@RequestParam() String search,
                                Model model) {
        model.addAttribute("drinksByName",drinksService.getDrinksByName(search));

        return "search";
    }

}
