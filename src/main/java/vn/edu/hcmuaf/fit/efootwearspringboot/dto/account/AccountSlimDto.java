package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.customer.CustomerDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.AddressDelivery;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSlimDto {
    private Long id;
    private String username;
    private String role;
    private Boolean isBlocked;
}
