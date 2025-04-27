package dev.haymon.ordermanagement.application.mapper;

import dev.haymon.ordermanagement.application.dto.order.OrderResponse;
import dev.haymon.ordermanagement.domain.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    OrderResponse toResponseDTO(Order order);
}
