package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery.AddressDeliveryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.AddressDelivery;

import java.util.List;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
@Component("addressDeliveryMapper")
public interface AddressDeliveryMapper {

    AddressDeliveryMapper INSTANCE = Mappers.getMapper(AddressDeliveryMapper.class);

    AddressDeliveryDto toDto(AddressDelivery AddressDelivery);

    AddressDelivery toEntity(AddressDeliveryDto AddressDeliveryDto);

    List<AddressDelivery> toEntities(List<AddressDeliveryDto> AddressDeliveryDtos);

    List<AddressDeliveryDto> toDtos(List<AddressDelivery> AddressDeliveries);
}
