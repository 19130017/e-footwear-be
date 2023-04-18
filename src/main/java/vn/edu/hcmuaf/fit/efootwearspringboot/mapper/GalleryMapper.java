package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery.GalleryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery.GallerySlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Gallery;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TypeGalleryMapper.class})
@Component("galleryMapper")
public interface GalleryMapper {
    GalleryMapper INSTANCE = Mappers.getMapper(GalleryMapper.class);

    GalleryDto toDto(Gallery gallery);

    GallerySlimDto toSlimDto(Gallery gallery);

    Gallery toEntity(GalleryDto galleryDto);

    List<Gallery> toEntities(List<GalleryDto> galleryDtos);

    List<GalleryDto> toDtos(List<Gallery> galleries);
}
