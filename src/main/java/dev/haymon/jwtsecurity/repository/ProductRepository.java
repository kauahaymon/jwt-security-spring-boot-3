package dev.haymon.jwtsecurity.repository;

import dev.haymon.jwtsecurity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
