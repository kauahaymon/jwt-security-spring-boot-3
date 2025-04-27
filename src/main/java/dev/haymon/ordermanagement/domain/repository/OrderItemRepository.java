package dev.haymon.ordermanagement.domain.repository;

import dev.haymon.ordermanagement.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
