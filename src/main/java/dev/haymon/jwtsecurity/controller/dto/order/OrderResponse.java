package dev.haymon.jwtsecurity.controller.dto.order;

import dev.haymon.jwtsecurity.model.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse {

    private Integer id;
    private LocalDateTime createdAt;
    private BigDecimal total;
    private List<OrderItemResponse> items;
}
