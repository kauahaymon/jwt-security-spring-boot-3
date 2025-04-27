package dev.haymon.ordermanagement.domain.repository;

import dev.haymon.ordermanagement.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
