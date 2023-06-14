package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.customer.CustomerDto;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountLoginFBRequestDto {
    @NotEmpty(message = "Không được để trống")
    private String fid;

    @NotEmpty(message = "Không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    private CustomerDto customer;
}
