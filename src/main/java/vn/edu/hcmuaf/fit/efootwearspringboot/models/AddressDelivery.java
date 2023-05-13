package vn.edu.hcmuaf.fit.efootwearspringboot.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Table(name = "address_delivery")
public class AddressDelivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "is_default")
    private Boolean isDefault;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account account;
    @Embedded
    private Address addresses;

    @Column(name = "state", length = 10)
    @Enumerated(value = EnumType.STRING)
    private EntityState state;
}
