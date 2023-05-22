package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UploadAvatarDto {
    @NotBlank(message = "Không được để trống")
    private String avatar;

    @NotNull(message = "Không được để trống")
    private Long accountId;
}
