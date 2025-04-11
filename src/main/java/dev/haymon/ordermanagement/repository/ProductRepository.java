package dev.haymon.ordermanagement.repository;

import dev.haymon.ordermanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
