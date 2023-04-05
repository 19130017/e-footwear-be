package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Size;

import java.util.List;

@Mapper(componentModel = "spring")
@Component("sizeMapper")
public interface SizeMapper {
    SizeMapper INSTANCE = Mappers.getMapper(SizeMapper.class);

    SizeDto toDto(Size Size);

    Size toEntity(SizeDto sizeDto);

    List<Size> toEntities(List<SizeDto> sizeDtos);

    List<SizeDto> toDtos(List<Size> sizes);
}
