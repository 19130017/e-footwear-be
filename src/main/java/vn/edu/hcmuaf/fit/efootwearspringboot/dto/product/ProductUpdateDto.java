package vn.edu.hcmuaf.fit.efootwearspringboot.dto.product;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeDto;

@Getter
@Setter
public class ProductUpdateDto {
    private Long id;
    private String name;
    private Integer stockQuantity;
    private Integer importQuantity;
    private Integer discountRate;
    private Integer originPrice;
    private String description;
    private CategoryDto category;
    private ColorDto color;
    private SizeDto size;
}
