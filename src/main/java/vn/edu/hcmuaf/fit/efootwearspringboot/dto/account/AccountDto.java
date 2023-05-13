package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.customer.CustomerDto;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Boolean isBlocked;
}
