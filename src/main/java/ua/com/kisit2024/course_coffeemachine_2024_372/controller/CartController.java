package ua.com.kisit2024.course_coffeemachine_2024_372.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kisit2024.course_coffeemachine_2024_372.bl.Cart;
import ua.com.kisit2024.course_coffeemachine_2024_372.bl.ItemCart;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.DrinkHasOrder;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.IngredientsHasDrink;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Order;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Users;
import ua.com.kisit2024.course_coffeemachine_2024_372.repository.DrinkHasOrderRepository;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.OrderService;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.UserService;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final UserService userService;
    private final OrderService orderService;
    private final DrinkHasOrderRepository drinkHasOrderRepository;

    @PostMapping("/addToCart")
    public String saveNewItemToCart(@RequestParam(name = "id") IngredientsHasDrink ingredientsHasDrink,
                                    @RequestParam(name = "quantity") int quantity,
                                    HttpServletRequest request) {

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        cart.addNewItemToCart(ingredientsHasDrink, quantity);
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

        Long userId = (Long) session.getAttribute("user");

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

        Long userId = (Long) session.getAttribute("user");

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