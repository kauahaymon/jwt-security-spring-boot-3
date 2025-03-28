package dev.haymon.jwtsecurity.controller;

import dev.haymon.jwtsecurity.controller.dto.product.ProductResponse;
import dev.haymon.jwtsecurity.controller.mapper.ProductMapper;
import dev.haymon.jwtsecurity.model.Product;
import dev.haymon.jwtsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(user -> ResponseEntity.ok(mapper.toDTO(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        Page<Product> products = service.getProductsPage(pageNumber, pageSize);
        Page<ProductResponse> responsePage = products.map(mapper::toDTO);

        return ResponseEntity.ok(responsePage);
    }
}
