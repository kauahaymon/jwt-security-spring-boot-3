package dev.haymon.jwtsecurity.controller.mapper;

import dev.haymon.jwtsecurity.controller.dto.order.OrderResponse;
import dev.haymon.jwtsecurity.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    OrderResponse toResponseDTO(Order order);
}
