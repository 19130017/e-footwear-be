package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.brand.BrandDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Brand;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDto toDto(Brand brand);

    Brand dtoToEntity(BrandDto brandDto);

    List<Brand> dtosToEntities(List<BrandDto> brandDtos);

    List<BrandDto> entitiesToDtos(List<Brand> brands);
}
