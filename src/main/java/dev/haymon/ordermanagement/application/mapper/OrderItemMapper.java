package dev.haymon.ordermanagement.application.mapper;

import dev.haymon.ordermanagement.application.dto.order.OrderItemResponse;
import dev.haymon.ordermanagement.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "subTotal", expression = "java(orderItem.getSubTotal())")
    OrderItemResponse toDTO(OrderItem orderItem);
}
