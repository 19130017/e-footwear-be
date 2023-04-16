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
    @Column(name = "slug")
    private String slug;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "discount_rate")
    private Integer discountRate;

    @Column(name = "origin_price")
    private Integer originPrice;

    @Column(name = "create_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private ZonedDateTime createAt;
    @Column(name = "update_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private ZonedDateTime updateAt;

    @Column(name = "state", length = 10)
    @Enumerated(value = EnumType.STRING)
    private EntityState state;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images;
    @OneToMany(mappedBy = "product")
    private List<ProductDetail> details;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne()
    @JoinColumn(name = "color_id")
    private Color color;

    @Transient
    private Integer colorCounter;
    @Transient
    private Integer sizeCounter;
    @Transient
    private Integer discountPrice;

    public Integer getDiscountPrice() {
        return originPrice - (originPrice * discountRate / 100);
    }
}
