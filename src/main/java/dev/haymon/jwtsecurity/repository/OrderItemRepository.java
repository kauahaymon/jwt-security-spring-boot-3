package dev.haymon.jwtsecurity.repository;

import dev.haymon.jwtsecurity.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
