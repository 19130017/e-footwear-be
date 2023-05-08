package vn.edu.hcmuaf.fit.efootwearspringboot.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "coupons")
public class Coupon  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "price")
    private Integer price;
    @Column(name = "code")
    private String code;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "max_usage")
    private Integer maxUsage;
}
