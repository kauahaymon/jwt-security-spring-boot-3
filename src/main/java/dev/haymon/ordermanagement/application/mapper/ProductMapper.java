package dev.haymon.ordermanagement.application.mapper;

import dev.haymon.ordermanagement.application.dto.product.ProductResponse;
import dev.haymon.ordermanagement.domain.model.Product;
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
