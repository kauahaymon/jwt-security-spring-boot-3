package dev.haymon.jwtsecurity.repository;

import dev.haymon.jwtsecurity.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
