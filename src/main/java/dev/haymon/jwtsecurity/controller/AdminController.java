package dev.haymon.jwtsecurity.controller;

import dev.haymon.jwtsecurity.controller.dto.UserResponse;
import dev.haymon.jwtsecurity.controller.dto.UserUpdateRequest;
import dev.haymon.jwtsecurity.controller.dto.product.ProductRequest;
import dev.haymon.jwtsecurity.controller.mapper.UserResponseMapper;
import dev.haymon.jwtsecurity.model.Product;
import dev.haymon.jwtsecurity.model.User;
import dev.haymon.jwtsecurity.service.AdminService;
import dev.haymon.jwtsecurity.service.ProductService;
import dev.haymon.jwtsecurity.util.UriUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;
    private final UserResponseMapper userMapper;
    private final ProductService productService;

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> listOfUsers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Page<User> users = service.getUsers(pageNumber, pageSize);
        Page<UserResponse> userResponses = users.map(userMapper::toDTO);
        return ResponseEntity.ok(userResponses);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Integer id,
            @RequestBody @Valid UserUpdateRequest request
    ) {
        service.updateUser(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        return service.getUserById(id)
                .map(user ->  ResponseEntity.ok(userMapper.toDTO(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/products")
    public ResponseEntity<?> registerProduct(
            @RequestBody @Valid ProductRequest request
    ) {
        Product savedProduct = productService.register(request);
        URI uri = UriUtil.generateLocation(savedProduct.getId());
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Integer id,
            @RequestBody @Valid ProductRequest request
    ) {
        productService.update(id, request);
        return ResponseEntity.noContent().build();
    }
}
