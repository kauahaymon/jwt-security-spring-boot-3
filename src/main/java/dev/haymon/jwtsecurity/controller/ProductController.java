package dev.haymon.jwtsecurity.controller;

import dev.haymon.jwtsecurity.controller.dto.ProductRegisterRequest;
import dev.haymon.jwtsecurity.model.Product;
import dev.haymon.jwtsecurity.service.ProductService;
import dev.haymon.jwtsecurity.util.UriUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<?> register(
            @RequestBody @Valid ProductRegisterRequest request
    ) {
        Product savedProduct = service.register(request);
        URI uri = UriUtil.generateLocation(savedProduct.getId());
        return ResponseEntity.created(uri).build();
    }
}
