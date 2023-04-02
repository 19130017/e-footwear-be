package vn.edu.hcmuaf.fit.efootwearspringboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "discountPrice")

    private Integer discountPrice;
    @Column(name = "discountRate")

    private Integer discountRate;
    @Column(name = "originPrice")

    private Integer originPrice;
    @Column(name = "quantity")

    private Integer quantity;
    @Column(name = "description")

    private String description;
    @Column(name = "createAt")

    private String createAt;
    @Column(name = "updateAt")

    private String updateAt;


}
