package ua.com.kisit2024.course_coffeemachine_2024_372.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Drinks;
import ua.com.kisit2024.course_coffeemachine_2024_372.repository.DrinksRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrinksService {

    private final DrinksRepository drinksRepository;

    public void save(Drinks drinks){
        drinksRepository.save(drinks);
    }

    public void update(Drinks drinks){
        drinksRepository.save(drinks);
    }

    public void delete(Drinks drinks){
        drinksRepository.delete(drinks);
    }

    public List<Drinks> findAllDrinks(){
        return drinksRepository.findAll();
    }

    public Optional<Drinks> findDrinksById(Long id){
        return drinksRepository.findById(id);
    }

    public void deleteAllDrinks(){
        drinksRepository.deleteAll();
    }

    public List<Drinks> getDrinksByName(String name) {
        return drinksRepository.findAllByNameContainsIgnoreCaseOrderByName(name);
    }

    public void saveDrinks(List<Drinks> drinks){
        drinksRepository.saveAll(drinks);
    }

}
