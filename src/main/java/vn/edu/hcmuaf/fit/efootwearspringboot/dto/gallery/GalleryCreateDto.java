package vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.type_gallery.TypeGalleryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.TypeGallery;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GalleryCreateDto {
    private TypeGalleryDto typeGallery;
    private String imageURL;
    private String link;
    private String title;

}
