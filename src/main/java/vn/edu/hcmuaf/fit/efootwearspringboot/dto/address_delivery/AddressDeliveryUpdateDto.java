package vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery;

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
public class AddressDeliveryUpdateDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Boolean isDefault;
    private AddressDto addresses;
}
