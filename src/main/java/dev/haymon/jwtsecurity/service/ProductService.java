package dev.haymon.jwtsecurity.service;

import dev.haymon.jwtsecurity.controller.dto.product.ProductRegisterRequest;
import dev.haymon.jwtsecurity.model.Product;
import dev.haymon.jwtsecurity.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product register(ProductRegisterRequest request) {
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
}
