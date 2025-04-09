package dev.haymon.jwtsecurity.controller;

import dev.haymon.jwtsecurity.controller.dto.order.OrderRequest;
import dev.haymon.jwtsecurity.model.Order;
import dev.haymon.jwtsecurity.service.OrderService;
import dev.haymon.jwtsecurity.util.UriUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static dev.haymon.jwtsecurity.util.UriUtil.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {
        Order order = service.placeOrder(request);
        URI uri = generateLocation(order.getId());
        return ResponseEntity.created(uri).build();
    }
}
