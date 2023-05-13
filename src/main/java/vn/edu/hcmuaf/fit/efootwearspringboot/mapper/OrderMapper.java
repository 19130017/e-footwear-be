package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Order;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {AccountMapper.class,
                CouponMapper.class,
                AddressDeliveryMapper.class,
                OrderItemMapper.class
        })
@Component("orderMapper")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(Order order);

    Order toEntity(OrderDto orderDto);

    List<Order> toEntities(List<OrderDto> orderDtos);

    List<OrderDto> toDtos(List<Order> orders);
}
