package vn.edu.hcmuaf.fit.efootwearspringboot.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.OrderStatus;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountSlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery.AddressDeliveryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon.CouponDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_item.OrderItemDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.AddressDelivery;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Coupon;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.OrderItem;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String id;
    private Integer cost;
    private Integer transportFee;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private CouponDto coupon;
    private AccountSlimDto account;
    // tạo slim không cần hiển thị account
    private AddressDeliveryDto address;
}
