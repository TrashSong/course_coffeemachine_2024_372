package ua.com.kisit2024.course_coffeemachine_2024_372.service;

import org.springframework.stereotype.Service;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Order;
import ua.com.kisit2024.course_coffeemachine_2024_372.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order saveNewOrderForUser(Order order){
        return orderRepository.save(order);
    }

}
