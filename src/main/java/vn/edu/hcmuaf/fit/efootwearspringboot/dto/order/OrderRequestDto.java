package vn.edu.hcmuaf.fit.efootwearspringboot.dto.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery.AddressDeliveryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon.CouponDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_item.OrderItemDto;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequestDto {
    @NotNull(message = "Không được để trống")
    private Integer cost;
    @NotNull(message = "Không được để trống")
    private Integer transportFee;
    @NotNull(message = "Không được để trống")
    private List<OrderItemDto> items;
    private String description;
    private CouponDto coupon;
    @NotNull(message = "Không được để trống")
    private AccountDto account;
    @NotNull(message = "Không được để trống")
    private AddressDeliveryDto address;
}
