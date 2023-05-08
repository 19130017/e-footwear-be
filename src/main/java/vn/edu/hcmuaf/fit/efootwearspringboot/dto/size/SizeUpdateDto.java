package vn.edu.hcmuaf.fit.efootwearspringboot.dto.size;

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
public class SizeUpdateDto {
    @NotNull(message = "Không được để trống size")
    private Integer value;
}
