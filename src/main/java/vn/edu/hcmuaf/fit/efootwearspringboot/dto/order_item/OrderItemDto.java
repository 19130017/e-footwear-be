package vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductOrderDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductSlimDto;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private ProductOrderDto product;
    private Integer quantity;
    private Integer price;
}
