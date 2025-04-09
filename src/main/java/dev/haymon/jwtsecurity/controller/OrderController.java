package dev.haymon.jwtsecurity.controller;

import dev.haymon.jwtsecurity.controller.dto.order.OrderRequest;
import dev.haymon.jwtsecurity.controller.dto.order.OrderResponse;
import dev.haymon.jwtsecurity.controller.mapper.OrderMapper;
import dev.haymon.jwtsecurity.model.Order;
import dev.haymon.jwtsecurity.service.OrderService;
import dev.haymon.jwtsecurity.util.UriUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static dev.haymon.jwtsecurity.util.UriUtil.*;

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
        Page<Order> pageOrders = service.getPageOrders(pageNumber, pageSize);
        Page<OrderResponse> orderResponses = pageOrders.map(mapper::toResponseDTO);

        return ResponseEntity.ok(orderResponses);
    }
}
