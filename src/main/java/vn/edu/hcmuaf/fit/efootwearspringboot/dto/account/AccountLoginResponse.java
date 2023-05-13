package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountLoginResponse {
    private Long accountId;
    private String username;
    private String avatar;
    private String token;
    private String refreshToken;
}
