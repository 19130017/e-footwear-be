package vn.edu.hcmuaf.fit.efootwearspringboot.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDto {
    private CategoryDto category;
    @NotBlank(message = "Không được để trống tên danh mục")
    private String name;
}
