package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.customer.CustomerDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Customer;

import java.util.List;

@Mapper(componentModel = "spring")
@Component("customerMapper")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);

    List<Customer> toEntities(List<CustomerDto> customerDtos);

    List<CustomerDto> toDtos(List<Customer> customers);
}
