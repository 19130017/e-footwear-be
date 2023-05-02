package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateDto {
    private String username;
    private String password;
    private String email;
}
