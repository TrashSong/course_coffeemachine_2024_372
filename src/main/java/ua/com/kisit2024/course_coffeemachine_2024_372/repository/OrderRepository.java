package ua.com.kisit2024.course_coffeemachine_2024_372.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
