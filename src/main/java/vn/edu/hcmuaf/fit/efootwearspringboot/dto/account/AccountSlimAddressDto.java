package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.customer.CustomerDto;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSlimAddressDto {
    private Long id;
    private String username;
    private String role;
    private Boolean isBlocked;
    private CustomerDto customer;
}
