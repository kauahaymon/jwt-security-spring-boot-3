package dev.haymon.ordermanagement.controller.mapper;

import dev.haymon.ordermanagement.controller.dto.product.ProductResponse;
import dev.haymon.ordermanagement.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toDTO(Product dto) {
        return ProductResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();
    }
}
