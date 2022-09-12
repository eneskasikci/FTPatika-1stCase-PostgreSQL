package com.example.ftpatika1stcasepostgresql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_product", allocationSize = 1)
    @GeneratedValue(generator = "seq_product", strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id")
    private long productId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;
    @Column(name = "product_Expiration")
    private LocalDate productExpireDate;

    // products can have more than one comments
    // so we need to create a list of comments
    // and we need to create a relationship between product and comment
    @OneToMany(mappedBy = "product", targetEntity = ProductComment.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductComment> productComments = new ArrayList<>();

}
