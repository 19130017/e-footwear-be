package vn.edu.hcmuaf.fit.efootwearspringboot.dto.product_image;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductSlimDto;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageCreateDto {
    private String imageURL;
    private ProductSlimDto product;
}
