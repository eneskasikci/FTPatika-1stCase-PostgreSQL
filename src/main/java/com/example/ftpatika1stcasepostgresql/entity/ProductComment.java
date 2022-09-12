package com.example.ftpatika1stcasepostgresql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_comments")

public class ProductComment {
    @Id
    @SequenceGenerator(name = "seq_product_comment", allocationSize = 1)
    @GeneratedValue(generator = "seq_product_comment", strategy = GenerationType.SEQUENCE)
    @Column(name = "product_comment_id")
    private long productCommentId;
    @Column(name = "product_comment", length = 500)
    private String productComment;
    @Column(name = "product_comment_date", nullable = false)
    private LocalDate productCommentDate = LocalDate.now();

    // we need to create a relationship between product and comment
    // so we need to create a product object
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
    private Product product;
    // we need to create a relationship between user and comment
    // so we need to create a user object
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;
}
