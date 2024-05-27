package ua.com.kisit2024.course_coffeemachine_2024_372.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Drinks;

import java.util.List;

public interface DrinksRepository extends JpaRepository<Drinks, Long> {

    List<Drinks> findAllByNameOrderByName(String name);

    List<Drinks> findAllByNameContainsIgnoreCaseOrderByName(String name);

}
