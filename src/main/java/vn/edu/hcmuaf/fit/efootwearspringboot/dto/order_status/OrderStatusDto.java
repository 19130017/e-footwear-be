package vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDto {
    private Long id;
    private String code;
    private String description;
}
