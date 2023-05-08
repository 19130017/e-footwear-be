package vn.edu.hcmuaf.fit.efootwearspringboot.dto.color;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ColorCreateDto {
    private String name;
    private String codeColor;
}
