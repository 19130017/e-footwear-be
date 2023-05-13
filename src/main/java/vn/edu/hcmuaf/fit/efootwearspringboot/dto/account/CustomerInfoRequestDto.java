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
public class CustomerInfoRequestDto {
    private Long accountId;
    private String firstName;
    private String lastName;
    private String birthday;
}
