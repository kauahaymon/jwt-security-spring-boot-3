package dev.haymon.jwtsecurity.controller.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemResponse {

    private Integer productId;
    private String nome;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal subTotal;
}
