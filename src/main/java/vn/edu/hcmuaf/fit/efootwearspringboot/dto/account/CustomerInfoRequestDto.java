package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CustomerInfoRequestDto {
    @NotNull(message = "Không được để trống")
    private Long accountId;

    @NotEmpty(message = "Không được để trống")
    private String firstName;

    @NotEmpty(message = "Không được để trống")
    private String lastName;

    @NotEmpty(message = "Không được để trống")
    private String birthday;
}
