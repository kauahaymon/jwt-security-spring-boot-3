package dev.haymon.ordermanagement.controller.mapper;

import dev.haymon.ordermanagement.controller.dto.order.OrderResponse;
import dev.haymon.ordermanagement.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    OrderResponse toResponseDTO(Order order);
}
