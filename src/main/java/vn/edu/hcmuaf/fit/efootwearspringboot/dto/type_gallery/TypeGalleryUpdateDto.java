package vn.edu.hcmuaf.fit.efootwearspringboot.dto.type_gallery;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeGalleryUpdateDto {
    @NotBlank(message = "Không được để trống")
    private String typeCode;
    @NotBlank(message = "Không được để trống")
    private String typeName;
}
