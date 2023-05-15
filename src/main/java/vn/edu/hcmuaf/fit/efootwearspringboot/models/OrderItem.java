package vn.edu.hcmuaf.fit.efootwearspringboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "order_items")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "detail_id")
    private Detail detail;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;
}
