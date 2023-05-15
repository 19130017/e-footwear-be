package vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductSlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeDto;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailUpdateDto {
    private Integer stockQuantity;
    private SizeDto size;
    private ProductSlimDto product;
}
