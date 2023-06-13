package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.customer.CustomerCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.customer.CustomerDto;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountLoginGGRequestDto {
    @NotEmpty(message = "Không được để trống")
    private String gid;

    @NotEmpty(message = "Không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    private CustomerDto customer;
}
