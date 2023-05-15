package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import jakarta.validation.constraints.NotEmpty;
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
public class AccountLoginRequest {
    @NotEmpty(message = "Không được để trống")
    private String username;

    @NotEmpty(message = "Không được để trống")
    private String password;
}
