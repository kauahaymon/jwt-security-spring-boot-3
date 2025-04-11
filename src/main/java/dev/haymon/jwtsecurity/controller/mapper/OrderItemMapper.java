package dev.haymon.jwtsecurity.controller.mapper;

import dev.haymon.jwtsecurity.controller.dto.order.OrderItemResponse;
import dev.haymon.jwtsecurity.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "subTotal", expression = "java(orderItem.getSubTotal())")
    OrderItemResponse toDTO(OrderItem orderItem);
}
