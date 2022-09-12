package com.example.ftpatika1stcasepostgresql.repo;

import com.example.ftpatika1stcasepostgresql.entity.Product;
import com.example.ftpatika1stcasepostgresql.entity.ProductComment;
import com.example.ftpatika1stcasepostgresql.entity.User;
import com.example.ftpatika1stcasepostgresql.repository.ProductCommentRepository;
import com.example.ftpatika1stcasepostgresql.repository.ProductRepository;
import com.example.ftpatika1stcasepostgresql.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductCommentRepositoryTest {

    @Autowired
    private ProductCommentRepository productCommentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        productCommentRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    private ProductComment createProductComment() {

        // Creating a product that has expiration date of +10 days from the current date
        Product product = new Product();
        product.setProductName("product");
        product.setProductPrice(BigDecimal.valueOf(100));
        product.setProductExpireDate(LocalDate.now().plusDays(10));
        productRepository.save(product);

        // Creating a user
        User user = new User();
        user.setUserName("FirstName");
        user.setUserLastName("LastName");
        user.setUserEmail("email@email.com");
        user.setUserPhoneNumber("123456789");
        userRepository.save(user);

        // Creating a product comment that is made by the user about the product
        // The comment date is the current date
        ProductComment productComment = new ProductComment();
        productComment.setProductComment("This is a dummy comment");
        productComment.setProductCommentDate(LocalDate.now());
        productComment.setProduct(product);
        productComment.setUser(user);
        productCommentRepository.save(productComment);

        return productComment;
    }

    // Returns all comments of a product
    @Test
    @DisplayName("There is a comment about a product")
    public void should_Return_Ok_When_There_Is_A_Comment_Of_A_Product() {
        ProductComment productComment = createProductComment();

        List<ProductComment> expected = productCommentRepository.findProductCommentsByProduct_ProductId
                (productComment.
                        getProduct().
                        getProductId()
                );

        assertThat(expected.size()).isEqualTo(1);
        assertThat(expected.get(0).getProductComment()).isEqualTo(productComment.getProductComment());
    }

    // Returns comments made between two dates of a product
    @Test
    @DisplayName("There is a comment about a product between two dates")
    public void should_Return_Ok_When_There_Is_A_Comment_Of_A_Product_Between_Two_Dates() {
        ProductComment productComment = createProductComment();

        // Dummy Product has a comment date of the current date.
        // This functions date range is between +5 -5 days from the current date.
        // It should pass everytime.
        List<ProductComment> expected = productCommentRepository.findProductCommentsByProduct_ProductIdAndProductCommentDateBetween
                (productComment.
                        getProduct().
                        getProductId(),
                        LocalDate.now().minusDays(5),
                        LocalDate.now().plusDays(5)
                );

        assertThat(expected.size()).isEqualTo(1);
    }

    // Returns comments made by a user
    @Test
    @DisplayName("There is a comment made by a user")
    public void should_Return_Ok_When_There_Is_A_Comment_Of_A_User() {
        ProductComment productComment = createProductComment();

        List<ProductComment> expected = productCommentRepository.findProductCommentsByUser_UserId
                (productComment.
                        getUser().
                        getUserId()
                );

        assertThat(expected.size()).isEqualTo(1);
    }

    // Returns comments made by a user between two dates
    @Test
    @DisplayName("There is a comment made by a user between two dates")
    public void should_Return_Ok_When_There_Is_A_Comment_Of_A_User_Between_Two_Dates() {
        ProductComment productComment = createProductComment();

        // Dummy Product has a comment date of the current date.
        // This functions date range is between +5 -5 days from the current date.
        // It should pass everytime.
        List<ProductComment> expected = productCommentRepository.findProductCommentsByUser_UserIdAndProductCommentDateBetween
                (productComment.
                        getUser().
                        getUserId(),
                        LocalDate.now().minusDays(5),
                        LocalDate.now().plusDays(5)
                );

        assertThat(expected.size()).isEqualTo(1);
    }
}
