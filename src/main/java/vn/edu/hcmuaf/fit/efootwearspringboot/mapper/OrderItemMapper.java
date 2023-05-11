package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_item.OrderItemDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.OrderItem;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
@Component("orderItemMapper")
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDto toDto(OrderItem orderItem);

    OrderItem toEntity(OrderItemDto orderItemDto);

    List<OrderItem> toEntities(List<OrderItemDto> orderItemDtos);

    List<OrderItemDto> toDtos(List<OrderItem> orderItems);
}
