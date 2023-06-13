package vn.edu.hcmuaf.fit.efootwearspringboot.dto.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerCreateDto {
    private String avatar;
    private String firstName;
    private String lastName;
    private Date birthday;
}
