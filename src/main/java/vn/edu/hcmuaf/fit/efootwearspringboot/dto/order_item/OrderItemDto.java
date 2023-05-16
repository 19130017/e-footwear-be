package vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailDto;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private DetailDto detail;
    private Integer quantity;
    private Integer price;
}
