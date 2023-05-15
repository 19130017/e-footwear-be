package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductOrderDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductSlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ColorMapper.class, ProductImageMapper.class})
@Component("productMapper")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductSlimDto toSlimDto(Product product);

    Product slimToEntity(ProductSlimDto productSlimDto);

    ProductOrderDto toOrderDto(Product product);

    List<ProductSlimDto> toSlimDtos(List<Product> products);


    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    List<Product> toEntities(List<ProductDto> productDtos);

    List<ProductDto> toDtos(List<Product> products);
}
