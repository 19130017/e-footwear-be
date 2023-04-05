package vn.edu.hcmuaf.fit.efootwearspringboot.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.EntityState;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "products")

public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "sku")
    private String sku;

    @Column(name = "slug")
    private String slug;

    @Column(name = "discount_rate")
    private Integer discountRate;

    @Column(name = "origin_price")
    private Integer originPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "create_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private ZonedDateTime createAt;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private ZonedDateTime updateAt;
    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private EntityState state;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne()
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne()
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne()
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImage;


    @Transient
    private Integer discountPrice;

    public Integer getDiscountPrice() {
        return originPrice - (originPrice * discountRate / 100);
    }
}
