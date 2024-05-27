package ua.com.kisit2024.course_coffeemachine_2024_372.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ingredients_has_drink")
public class IngredientsHasDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drinks _drink;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Sizes _size;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredients _ingredient;

    @OneToMany(mappedBy = "_ingredientsHasDrink")
    private List<DrinkHasOrder> ingredientsHasDrinkList;

}
