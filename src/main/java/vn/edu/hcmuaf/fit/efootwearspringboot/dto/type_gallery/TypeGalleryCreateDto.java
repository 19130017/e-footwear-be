package vn.edu.hcmuaf.fit.efootwearspringboot.dto.type_gallery;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeGalleryCreateDto {
    private String typeCode;
    private String typeName;
}
