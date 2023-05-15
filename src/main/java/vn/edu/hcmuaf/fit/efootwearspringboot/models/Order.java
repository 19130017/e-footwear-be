package vn.edu.hcmuaf.fit.efootwearspringboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "cost")
    private Integer cost; // giá sau khi + VAT + phí vận chuyển

    @Column(name = "transport_fee")
    private Integer transportFee; // giá vận chuyển

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @ManyToOne()
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "address_id")
    private AddressDelivery address;

    @ManyToOne()
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;
}
