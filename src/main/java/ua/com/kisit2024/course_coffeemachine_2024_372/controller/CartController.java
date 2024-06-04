package ua.com.kisit2024.course_coffeemachine_2024_372.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kisit2024.course_coffeemachine_2024_372.bl.Cart;
import ua.com.kisit2024.course_coffeemachine_2024_372.bl.ItemCart;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.*;
import ua.com.kisit2024.course_coffeemachine_2024_372.repository.DrinkHasOrderRepository;
import ua.com.kisit2024.course_coffeemachine_2024_372.repository.IngredientsRepository;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.OrderService;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final UserService userService;
    private final OrderService orderService;
    private final DrinkHasOrderRepository drinkHasOrderRepository;
    private final IngredientsRepository ingredientsRepository;

    @PostMapping("/addToCart")
    public String saveNewItemToCart(@RequestParam(name = "id") Drinks drinks,
                                    @RequestParam(name = "quantity") int quantity,
                                    @RequestParam(name = "v1") String[] array,
                                    @RequestParam(name = "v2") String size,
                                    HttpServletRequest request) {

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        List<Ingredients> ingredients = new ArrayList<>();
                Stream.of(array).forEach(el-> {
                    if(!el.equals("0")) ingredients.add(ingredientsRepository.findById(Long.valueOf(el)).get());
                }
                );
        System.out.println(ingredients);

        if (cart == null) {
            cart = new Cart();
        }

        List<IngredientsHasDrink> ingredientsHasDrinkList = new ArrayList<>();
        for (Ingredients el: ingredients
             ) {
            ingredientsHasDrinkList.add(new IngredientsHasDrink(drinks, new Sizes(), el));
        }

        for (IngredientsHasDrink el: ingredientsHasDrinkList
             ) {
            cart.addNewItemToCart(el, quantity);
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";

    }

    @GetMapping("/cart")
    public String getPageCart(HttpServletRequest request,
                              Model model) {

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        model.addAttribute("cart", cart.getCart());
        model.addAttribute("total", cart.getTotalValue());
        model.addAttribute("el", cart.getSumElInCart());

        return "cart";

    }

    @PostMapping("/deleteItemFromCart")
    public String deleteItemFromCart(@RequestParam(name = "id") IngredientsHasDrink ingredientsHasDrink,
                                     HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        cart.deleteItemFromCart(ingredientsHasDrink);

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }


    @PostMapping("/deleteAllItem")
    public String deleteAllItemFromCart(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        cart.deleteAllItemFromCart();
        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    @GetMapping("/order")
    public String getPageOrder(HttpServletRequest request,
                               Model model) {

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        model.addAttribute("cart", cart.getCart());
        model.addAttribute("total", cart.getTotalValue());
        model.addAttribute("el", cart.getSumElInCart());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) userService.loadUserByUsername(auth.getName());
        Long userId = users.getId();

        return "order";
    }

    @PostMapping("/buy")
    public String buyDrink(@RequestParam(name = "payment") String paymentId,
                           HttpServletRequest request){

        String payment = (paymentId.equals("1")) ? "Готівка" : "Банківська карта";

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart==null){
            return "redirect:/";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) userService.loadUserByUsername(auth.getName());
        Long userId = users.getId();

        if (userId!=null){

            Users user = userService.getUserById(userId);

            Order order = new Order();
            order.setPayment(payment);
            order.setDateCreated(new Date());
            order.setUser(user);

            Order order1 = orderService.saveNewOrderForUser(order);

            for (ItemCart el : cart.getCart()) {
                drinkHasOrderRepository.save(new DrinkHasOrder(el.getIngredientsHasDrink(), order1, el.getQuantity()));
            }

            return "redirect:/thank";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/thank")
    public String getPageThank() {
        return "thank";
    }

}