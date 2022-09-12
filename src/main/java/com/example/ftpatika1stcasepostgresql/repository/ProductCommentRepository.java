package com.example.ftpatika1stcasepostgresql.repository;

import com.example.ftpatika1stcasepostgresql.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

    // Returns all comments of a product
    List<ProductComment> findProductCommentsByProduct_ProductId(Long productId);

    // Returns comments made between two dates of a product
    List<ProductComment> findProductCommentsByProduct_ProductIdAndProductCommentDateBetween(long productId, LocalDate minusDays, LocalDate plusDays);

    // Returns comments made by a user
    List<ProductComment> findProductCommentsByUser_UserId(Long userId);

    // Returns comments made by a user between two dates
    List<ProductComment> findProductCommentsByUser_UserIdAndProductCommentDateBetween(Long userId, LocalDate productCommentDate, LocalDate productCommentDate2);
}
