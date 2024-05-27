package ua.com.kisit2024.course_coffeemachine_2024_372.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Drinks;
import ua.com.kisit2024.course_coffeemachine_2024_372.repository.DrinksRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinksService {

    private final DrinksRepository drinksRepository;

    public List<Drinks> findAllDrinks() {
        return drinksRepository.findAll();
    }

    public List<Drinks> getProductsByName(String name) {
        return drinksRepository.findAllByNameContainsIgnoreCaseOrderByName(name);
    }

}
