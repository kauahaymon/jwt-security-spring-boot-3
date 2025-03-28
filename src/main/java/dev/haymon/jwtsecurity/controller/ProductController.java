package dev.haymon.jwtsecurity.controller;

import dev.haymon.jwtsecurity.controller.dto.product.ProductRegisterRequest;
import dev.haymon.jwtsecurity.controller.dto.product.ProductResponse;
import dev.haymon.jwtsecurity.controller.mapper.ProductMapper;
import dev.haymon.jwtsecurity.model.Product;
import dev.haymon.jwtsecurity.service.ProductService;
import dev.haymon.jwtsecurity.util.UriUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @PostMapping
    public ResponseEntity<?> register(
            @RequestBody @Valid ProductRegisterRequest request
    ) {
        Product savedProduct = service.register(request);
        URI uri = UriUtil.generateLocation(savedProduct.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(user -> ResponseEntity.ok(mapper.toDTO(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
       service.deleteById(id);
       return ResponseEntity.noContent().build();
    }
}
