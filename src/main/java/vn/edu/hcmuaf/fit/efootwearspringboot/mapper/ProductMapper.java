package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, BrandMapper.class, ColorMapper.class, SizeMapper.class})
@Component("productMapper")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);

    @InheritInverseConfiguration
    Product toEntity(ProductDto productDto);

    List<Product> toEntities(List<ProductDto> productDtos);

    List<ProductDto> toDtos(List<Product> products);
}
