package com.example.ftpatika1stcasepostgresql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")

public class User {

    @Id
    @SequenceGenerator(name = "seq_user", allocationSize = 1)
    @GeneratedValue(generator = "seq_user", strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;
    @Column(name = "user_lastname", length = 50, nullable = false)
    private String userLastName;
    @Column(name = "user_email", length = 50, nullable = false)
    private String userEmail;
    @Column(name = "user_phoneNumber", length = 15, nullable = false)
    private String userPhoneNumber;

    // users can have more than one product comments
    // so we need to create a list of product comments
    // and we need to create a relationship between user and product comment
    @OneToMany(mappedBy = "user", targetEntity = ProductComment.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductComment> productComments = new ArrayList<>();
}
