package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDto {
    private String username;
    private String password;
    private String email;
    private String refreshToken;
    private String role;
    private Boolean isBlocked;
}
