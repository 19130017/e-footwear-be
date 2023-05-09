package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductSlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ColorMapper.class, ProductImageMapper.class, DetailMapper.class})
@Component("productMapper")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductSlimDto toSlimDto(Product product);


    Product slimToEntity(ProductSlimDto productSlimDto);

//    List<Product> slimToEntities(List<ProductSlimDto> productSlimDtos);

    List<ProductSlimDto> toSlimDtos(List<Product> products);


    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    List<Product> toEntities(List<ProductDto> productDtos);

    List<ProductDto> toDtos(List<Product> products);
}
