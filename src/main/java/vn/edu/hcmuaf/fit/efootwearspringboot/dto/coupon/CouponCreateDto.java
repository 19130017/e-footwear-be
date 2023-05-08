package vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CouponCreateDto {
    @NotNull(message = "Không được để trống giá giảm")
    private Integer price;
    @NotBlank(message = "Không được để trống mã giảm giá")
    private String code;
    @NotNull(message = "Không được để trống số lần sử dụng")
    private Integer maxUsage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    @NotNull(message = "Không được để trống thời gian kết thúc")
    private Date endTime;
}
