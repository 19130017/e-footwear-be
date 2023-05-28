package vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductOrderDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductSlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeDto;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailCreateDto {
    @NotNull(message = "Không được để trống số lượng sản phẩm")
    private Integer stockQuantity;
    @NotNull(message = "Không được để trống kích thước sản phẩm")
    private SizeDto size;
    private ProductOrderDto product;
}
