package dev.haymon.jwtsecurity.controller.mapper;

import dev.haymon.jwtsecurity.controller.dto.order.OrderItemResponse;
import dev.haymon.jwtsecurity.controller.dto.order.OrderResponse;
import dev.haymon.jwtsecurity.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderResponse toResponseDTO(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems()
                .stream()
                .map(orderItem -> OrderItemResponse.builder()
                        .productId(orderItem.getId())
                        .nome(orderItem.getProduct().getName())
                        .quantity(orderItem.getQuantity())
                        .unitPrice(orderItem.getUnitPrice())
                        .build())
                        .toList();

        return OrderResponse.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .total(order.getTotal())
                .items(itemResponses)
                .build();
    }
}
