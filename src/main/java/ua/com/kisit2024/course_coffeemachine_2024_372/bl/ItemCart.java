package ua.com.kisit2024.course_coffeemachine_2024_372.bl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Drinks;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.IngredientsHasDrink;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCart {

    private IngredientsHasDrink ingredientsHasDrink;
    private int quantity;

}
