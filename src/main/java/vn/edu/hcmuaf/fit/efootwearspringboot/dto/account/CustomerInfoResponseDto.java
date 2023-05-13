package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

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
public class CustomerInfoResponseDto {
    private String firstName;
    private String lastName;
    private String birthday;

    private String avatar;
}
