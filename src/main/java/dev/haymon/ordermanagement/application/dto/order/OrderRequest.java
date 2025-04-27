package dev.haymon.ordermanagement.application.dto.order;

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
