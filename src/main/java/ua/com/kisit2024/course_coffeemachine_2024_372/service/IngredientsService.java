package ua.com.kisit2024.course_coffeemachine_2024_372.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Ingredients;
import ua.com.kisit2024.course_coffeemachine_2024_372.repository.IngredientsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    public List<Ingredients> findAllIngredients() {
        return ingredientsRepository.findAll();
    }

}
