package dev.haymon.ordermanagement.repository;

import dev.haymon.ordermanagement.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
