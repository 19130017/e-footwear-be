package vn.edu.hcmuaf.fit.efootwearspringboot.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product_image.ProductImageDto;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderDto {
    public Long id;
    private String name;
    private String slug;
    private Integer discountPrice;
    private Integer discountRate;
    private Integer originPrice;
    private ColorDto color;
    private List<ProductImageDto> images;
    private CategoryDto category;
}
