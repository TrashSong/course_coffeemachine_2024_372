package ua.com.kisit2024.course_coffeemachine_2024_372.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.IngredientsHasDrink;

public interface IngredientsHasDrinkRepository extends JpaRepository<IngredientsHasDrink, Long> {
}
