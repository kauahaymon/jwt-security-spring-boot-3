package dev.haymon.ordermanagement.application.controller;

import dev.haymon.ordermanagement.application.dto.order.OrderRequest;
import dev.haymon.ordermanagement.application.dto.order.OrderResponse;
import dev.haymon.ordermanagement.application.mapper.OrderMapper;
import dev.haymon.ordermanagement.domain.model.Order;
import dev.haymon.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static dev.haymon.ordermanagement.util.UriUtil.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {
        Order order = service.placeOrder(request);
        URI uri = generateLocation(order.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getOrders(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Page<Order> pageOrders = service.getUserOrders(pageNumber, pageSize);
        Page<OrderResponse> orderResponses = pageOrders.map(mapper::toResponseDTO);

        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable Integer id) {
        return service.gerOrderDetails(id)
                .map(order -> ResponseEntity.ok(mapper.toResponseDTO(order)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
