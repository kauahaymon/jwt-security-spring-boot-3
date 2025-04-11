package dev.haymon.ordermanagement.controller;

import dev.haymon.ordermanagement.controller.dto.order.OrderResponse;
import dev.haymon.ordermanagement.controller.dto.product.ProductRequest;
import dev.haymon.ordermanagement.controller.dto.user.UserResponse;
import dev.haymon.ordermanagement.controller.dto.user.UserUpdateRequest;
import dev.haymon.ordermanagement.controller.mapper.OrderMapper;
import dev.haymon.ordermanagement.controller.mapper.UserResponseMapper;
import dev.haymon.ordermanagement.model.Product;
import dev.haymon.ordermanagement.model.User;
import dev.haymon.ordermanagement.service.AdminService;
import dev.haymon.ordermanagement.service.OrderService;
import dev.haymon.ordermanagement.service.ProductService;
import dev.haymon.ordermanagement.util.UriUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final OrderService orderService;
    private final OrderMapper orderMapper;

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

    @GetMapping("/orders")
    public ResponseEntity<Page<OrderResponse>> listAllOrders(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam(required = false, defaultValue = "id") String sort,
            @RequestParam(required = false, defaultValue = "asc") String direction
    ) {
        Sort sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.by(sort).descending()
                : Sort.by(sort).ascending();

        Pageable pageable = PageRequest.of(page, size, sortDirection);

        Page<OrderResponse> orderResponses = orderService.getAllOrders(pageable)
                .map(orderMapper::toResponseDTO);

        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<OrderResponse>> listOrdersByUser(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @PathVariable("userId") Integer userId
    ) {
        Page<OrderResponse> orderResponses = orderService
                .getUserOrders(page, size, userId)
                .map(orderMapper::toResponseDTO);
        return ResponseEntity.ok(orderResponses);
    }
}
