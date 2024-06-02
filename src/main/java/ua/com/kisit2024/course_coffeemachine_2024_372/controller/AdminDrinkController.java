package ua.com.kisit2024.course_coffeemachine_2024_372.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Drinks;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.DrinksService;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.SaveDrinkToDBFromExcel;
import ua.com.kisit2024.course_coffeemachine_2024_372.validation.Valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminDrinkController {

    private final DrinksService drinksService;
    private final SaveDrinkToDBFromExcel saveDrinkToDBFromExcel;

    @GetMapping("/drink-admin")
    public String getDrinkAdminPage(Model model){

        model.addAttribute("drinks", drinksService.findAllDrinks());

        return "admin_drinks";
    }

    @PostMapping("/saveNewDrink")
    public String saveNewDrink(@RequestParam(name = "name") String name,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "link") String link,
                               @RequestParam(name = "price") String price
                               ){
        Drinks drinks = new Drinks();
        drinks.setName(name);
        drinks.setDescription(description);
        drinks.setImage(link);
        drinks.setPrice(new BigDecimal(Double.valueOf(price)));

        drinksService.save(drinks);

        return "redirect:/drink-admin";
    }

    @PostMapping("/updateDrink")
    public String updateDrink(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "name") String name,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "image") String link,
                               @RequestParam(name = "price") String price
    ){
        Drinks drinks = new Drinks();
        drinks.setId(id);
        drinks.setName(name);
        drinks.setDescription(description);
        drinks.setImage(link);
        drinks.setPrice(new BigDecimal(Double.valueOf(price)));

        drinksService.update(drinks);

        return "redirect:/drink-admin";
    }

    @PostMapping("/deleteDrink")
    public String deleteDrink(@RequestParam(name = "id") Drinks drinks){
        drinksService.delete(drinks);

        return "redirect:/drink-admin";
    }

    @PostMapping("saveFromExcel")
    public String saveDrinkToDbFromExcel(@RequestParam("file") MultipartFile file){

        List<Drinks> drinks = new ArrayList<>();

        String path = "C:\\Procrastination\\Memes\\" + file.getOriginalFilename();

        if(file !=null && !file.getOriginalFilename().isEmpty()) {

            Valid valid = new Valid();
            if (valid.logicXLS(file.getOriginalFilename())) {
                drinks = saveDrinkToDBFromExcel.saveListDrinkToDbFromExcel(path);
                drinksService.saveDrinks(drinks);
            } else if (valid.logicXLSX(file.getOriginalFilename())) {
                drinks = saveDrinkToDBFromExcel.saveListDrinkToDbFromExcel2(path);
                drinksService.saveDrinks(drinks);
            }
        }
        return "redirect:/drink-admin";
    }

}
