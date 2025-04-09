package dev.haymon.jwtsecurity.controller.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderRequest {

    private List<OrderItemRequest> items;
}
