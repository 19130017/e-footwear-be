package vn.edu.hcmuaf.fit.efootwearspringboot.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.PaymentMethod;

import java.io.Serializable;
import java.time.ZonedDateTime;
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

    @Column(name = "paymentMethod")
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

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

    @Column(name = "order_time", updatable = false)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private ZonedDateTime orderTime;
}
