package dev.haymon.ordermanagement.controller.mapper;

import dev.haymon.ordermanagement.controller.dto.order.OrderItemResponse;
import dev.haymon.ordermanagement.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "subTotal", expression = "java(orderItem.getSubTotal())")
    OrderItemResponse toDTO(OrderItem orderItem);
}
