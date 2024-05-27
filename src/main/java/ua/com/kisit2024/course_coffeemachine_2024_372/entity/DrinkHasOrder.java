package ua.com.kisit2024.course_coffeemachine_2024_372.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "drink_has_order")
public class DrinkHasOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingredients_has_drink_id")
    private IngredientsHasDrink _ingredientsHasDrink;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order _order;

    private int quantity;

    public DrinkHasOrder(IngredientsHasDrink _ingredientsHasDrink, Order _order, int quantity) {
        this._ingredientsHasDrink = _ingredientsHasDrink;
        this._order = _order;
        this.quantity = quantity;
    }
}
