package vn.edu.hcmuaf.fit.efootwearspringboot.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery.AddressDeliveryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon.CouponDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_item.OrderItemDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_status.OrderStatusDto;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private String id;
    private Integer cost;
    private Integer transportFee;
    private OrderStatusDto orderStatus;
    private String description;
    private List<OrderItemDto> items;
    private CouponDto coupon;
    private AddressDeliveryDto address;
}
