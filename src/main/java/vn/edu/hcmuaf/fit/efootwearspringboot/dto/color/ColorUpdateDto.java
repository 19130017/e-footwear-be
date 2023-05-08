package vn.edu.hcmuaf.fit.efootwearspringboot.dto.color;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ColorUpdateDto {
    private String name;
    private String codeColor;
}
