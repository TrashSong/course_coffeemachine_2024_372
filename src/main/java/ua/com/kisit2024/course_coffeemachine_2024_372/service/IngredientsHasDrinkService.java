package ua.com.kisit2024.course_coffeemachine_2024_372.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.IngredientsHasDrink;
import ua.com.kisit2024.course_coffeemachine_2024_372.repository.IngredientsHasDrinkRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientsHasDrinkService {

    private final IngredientsHasDrinkRepository ingredientsHasDrinkRepository;

    public List<IngredientsHasDrink> findAllIngredientsHasDrink(){
        return ingredientsHasDrinkRepository.findAll();
    }

}
