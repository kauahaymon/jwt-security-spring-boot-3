package dev.haymon.jwtsecurity.controller.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemRequest {

    private Integer productId;
    private Integer quantity;
}
