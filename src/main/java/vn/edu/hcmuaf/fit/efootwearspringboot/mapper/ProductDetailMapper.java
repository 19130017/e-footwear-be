package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.ProductDetailDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.ProductDetail;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SizeMapper.class})
@Component("detailMapper")
public interface ProductDetailMapper {
    ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);

    ProductDetailDto toDto(ProductDetail productDetail);

    ProductDetail toEntity(ProductDetailDto productDetailDto);

    List<ProductDetail> toEntities(List<ProductDetailDto> productDetailDtos);

    List<ProductDetailDto> toDtos(List<ProductDetail> productDetail);
}
