package dev.haymon.jwtsecurity.repository;

import dev.haymon.jwtsecurity.model.Order;
import dev.haymon.jwtsecurity.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findByUser(User currentUser, Pageable orderPage);

    Page<Order> findByUserId(Integer id, Pageable orderPage);
}
