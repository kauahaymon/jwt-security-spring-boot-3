package dev.haymon.jwtsecurity.controller.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductResponse {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String description;
}
