package com.example.ftpatika1stcasepostgresql.repository;

import com.example.ftpatika1stcasepostgresql.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Query method to find products that are expired
    @Query("SELECT p FROM Product p WHERE p.productExpireDate < CURRENT_DATE")
    List<Product> findProductByProductExpireDateBeforeCurrentDate();

    // Query method to find products that are not expired
    @Query("SELECT p FROM Product p WHERE p.productExpireDate > CURRENT_DATE OR p.productExpireDate IS NULL")
    List<Product> findProductByProductExpireDateAfterCurrentDate();

}
