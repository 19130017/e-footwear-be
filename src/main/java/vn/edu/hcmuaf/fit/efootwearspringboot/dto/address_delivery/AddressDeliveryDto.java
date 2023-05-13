package vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDeliveryDto {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private Boolean isDefault;
    private AddressDto addresses;
}
