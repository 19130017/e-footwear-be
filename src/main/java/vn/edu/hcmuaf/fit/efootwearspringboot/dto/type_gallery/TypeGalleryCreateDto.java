package vn.edu.hcmuaf.fit.efootwearspringboot.dto.type_gallery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeGalleryCreateDto {
    @NotBlank(message = "Không được để trống mã loại")
    private String typeCode;
    @NotBlank(message = "Không được để trống tên loại")
    private String typeName;
}
