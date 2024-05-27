package ua.com.kisit2024.course_coffeemachine_2024_372.bl;

import lombok.Getter;
import lombok.Setter;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.IngredientsHasDrink;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Cart {

    List<ItemCart> cart;
    private double totalValue;
    private int sumElInCart;

    public Cart() {
        this.cart = new ArrayList<>();
        this.totalValue = 0;
        this.sumElInCart = 0;
    }

    public synchronized void addNewItemToCart(IngredientsHasDrink ingredientsHasDrink, int quantity) {

        boolean logic = true;

        for(ItemCart el: cart){
            if(el.getIngredientsHasDrink().getId()==ingredientsHasDrink.getId()){
                logic = false;
                el.setQuantity(el.getQuantity()+quantity);
            }
        }

        if(logic) cart.add(new ItemCart(ingredientsHasDrink, quantity));

    }

    public synchronized void deleteItemFromCart(IngredientsHasDrink ingredientsHasDrink) {

        for (ItemCart el : cart) {
            if(el.getIngredientsHasDrink().getId() == ingredientsHasDrink.getId()) {
                cart.remove(el);
                break;
            }
        }

    }

    public synchronized void deleteAllItemFromCart() {
        cart.clear();
        totalValue = 0;
        sumElInCart = 0;
    }

    public synchronized double getTotalValue() {

        totalValue = 0;

        for (ItemCart el : cart) {
            totalValue += (el.getIngredientsHasDrink().get_drink().getPrice().doubleValue()
                    + el.getIngredientsHasDrink().get_ingredient().getPrice().doubleValue())
                    * el.getIngredientsHasDrink().get_size().getPriceMultiplier();
        }

        return totalValue;

    }

    public synchronized int getSumElInCart() {
        int a = cart.size();
        sumElInCart = a;
        return a;
    }

}
