package vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Không được để trống loại ảnh")
    private TypeGalleryDto typeGallery;
    @NotBlank(message = "Không được để trống ảnh")
    private String imageURL;
    @NotBlank(message = "Không được để trống liên kết")
    private String link;
    @NotBlank(message = "Không được để trống mô tả")
    private String title;

}
