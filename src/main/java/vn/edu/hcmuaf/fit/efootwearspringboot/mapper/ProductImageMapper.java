package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product_image.ProductImageDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.ProductImage;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
@Component("productImageMapper")
public interface ProductImageMapper {
    ProductImageMapper INSTANCE = Mappers.getMapper(ProductImageMapper.class);

    @Mapping(source = "productImage.product", target = "product")
    ProductImageDto toDto(ProductImage productImage);

    @InheritInverseConfiguration
    ProductImage toEntity(ProductImageDto productImageDto);

    List<ProductImage> toEntities(List<ProductImageDto> productImageDtos);

    List<ProductImageDto> toDtos(List<ProductImage> productImages);
}
