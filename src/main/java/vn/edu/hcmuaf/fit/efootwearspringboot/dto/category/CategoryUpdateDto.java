package vn.edu.hcmuaf.fit.efootwearspringboot.dto.category;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDto {

    private Long id;
    private CategoryDto category;
    private String name;
}
