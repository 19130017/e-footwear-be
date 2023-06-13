package vn.edu.hcmuaf.fit.efootwearspringboot.dto.customer;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.AddressDelivery;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Order;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String avatar;
    private String firstName;
    private String lastName;
    private Date birthday;
}
