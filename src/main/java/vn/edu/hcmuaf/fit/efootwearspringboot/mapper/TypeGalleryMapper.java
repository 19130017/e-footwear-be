package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.type_gallery.TypeGalleryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.TypeGallery;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SizeMapper.class})
@Component("typeGalleryMapper")
public interface TypeGalleryMapper {
    TypeGalleryMapper INSTANCE = Mappers.getMapper(TypeGalleryMapper.class);

    TypeGalleryDto toDto(TypeGallery typeGallery);

    TypeGallery toEntity(TypeGalleryDto typeGalleryDto);

    List<TypeGallery> toEntities(List<TypeGalleryDto> typeGalleryDtos);

    List<TypeGalleryDto> toDtos(List<TypeGallery> typeGalleries);
}
