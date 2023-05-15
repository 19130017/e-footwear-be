package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_status.OrderStatusDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.OrderStatus;

import java.util.List;

@Mapper(componentModel = "spring")
@Component("orderStatusMapper")
public interface OrderStatusMapper {
    OrderStatusMapper INSTANCE = Mappers.getMapper(OrderStatusMapper.class);

    OrderStatusDto toDto(OrderStatus orderStatus);

    OrderStatus toEntity(OrderStatusDto orderStatusDto);

    List<OrderStatus> toEntities(List<OrderStatusDto> orderStatusDtos);

    List<OrderStatusDto> toDtos(List<OrderStatus> orderStatus);
}
