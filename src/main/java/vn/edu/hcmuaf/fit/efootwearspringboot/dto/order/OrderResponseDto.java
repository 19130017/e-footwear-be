package vn.edu.hcmuaf.fit.efootwearspringboot.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery.AddressDeliveryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon.CouponDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_item.OrderItemDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_status.OrderStatusDto;

import java.time.ZonedDateTime;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private ZonedDateTime orderTime;
    private AddressDeliveryDto address;
}
