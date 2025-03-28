package dev.haymon.jwtsecurity.service;

import dev.haymon.jwtsecurity.controller.dto.product.ProductRequest;
import dev.haymon.jwtsecurity.model.Product;
import dev.haymon.jwtsecurity.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product register(ProductRequest request) {
        Product product = Product
                .builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        return repository.save(product);
    }

    public Optional<Product> getById(Integer id) {
        return repository.findById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void update(Integer id, ProductRequest request) {
        repository.findById(id).map(product -> {
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setDescription(request.getDescription());
            return repository.save(product);
        }).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }
}
