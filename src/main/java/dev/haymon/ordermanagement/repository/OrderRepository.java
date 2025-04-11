package dev.haymon.ordermanagement.repository;

import dev.haymon.ordermanagement.model.Order;
import dev.haymon.ordermanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findByUser(User currentUser, Pageable orderPage);

    Page<Order> findByUserId(Integer id, Pageable orderPage);
}
