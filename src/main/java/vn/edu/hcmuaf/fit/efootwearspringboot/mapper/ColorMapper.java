package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Color;

import java.util.List;

@Mapper(componentModel = "spring")
@Component("colorMapper")
public interface ColorMapper {
    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

    ColorDto toDto(Color color);

    Color toEntity(ColorDto colorDto);

    List<Color> toEntities(List<ColorDto> colorDtos);

    List<ColorDto> toDtos(List<Color> colors);
}
