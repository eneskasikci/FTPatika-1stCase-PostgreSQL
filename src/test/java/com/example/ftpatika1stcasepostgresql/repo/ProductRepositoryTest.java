package com.example.ftpatika1stcasepostgresql.repo;

import com.example.ftpatika1stcasepostgresql.entity.Product;
import com.example.ftpatika1stcasepostgresql.repository.ProductRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository.deleteAll();
    }

    // creating multiple products
    // There will be total of 10 products
    // 5 of them will have expiration date of random date between 1 and 10 days before the current date
    private List<Product> createTestProducts() {
        Random random = new Random();
        int upperbound = 10;
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Product product = new Product();
            product.setProductName("GoodProduct" + i);
            product.setProductPrice(BigDecimal.valueOf(100));
            product.setProductExpireDate(LocalDate.now().plusDays(random.nextInt(upperbound)));
            products.add(product);
        }
        for (int i = 1; i <= 5; i++) {
            Product product = new Product();
            product.setProductName("ExpiredProduct" + i);
            product.setProductPrice(BigDecimal.valueOf(100));
            product.setProductExpireDate(LocalDate.now().minusDays(random.nextInt(upperbound)));
            products.add(product);
        }
        return products;
    }

    @Test
    @DisplayName("Test if we can find the products that are expired")
    public void should_Return_Ok_If_There_Is_A_Product_Expired() {
        List<Product> products = createTestProducts();
        productRepository.saveAll(products);
        List<Product> expiredProducts = productRepository.findProductByProductExpireDateBeforeCurrentDate();
        // print the expired date of the products
        if (expiredProducts.size() > 0) {
            assertThat(expiredProducts.size()).isGreaterThan(0);
            System.out.println("There are " + expiredProducts.size() + " expired products.");
            for (Product product : expiredProducts) {
                System.out.println(product.getProductName() + " - " + product.getProductExpireDate());
            }
        }
    }

    @Test
    @DisplayName("Test if we can find the products that are *NOT* expired.")
    public void should_Return_Ok_If_There_Is_A_Product_Not_Expired() {
        List<Product> products = createTestProducts();
        productRepository.saveAll(products);
        List<Product> notexpiredProducts = productRepository.findProductByProductExpireDateAfterCurrentDate();
        // print the not expired date of the products
        if (notexpiredProducts.size() > 0) {
            assertThat(notexpiredProducts.size()).isGreaterThan(0);
            System.out.println("There are " + notexpiredProducts.size() + " products that is not expired.");
            for (Product product : notexpiredProducts) {
                System.out.println(product.getProductName() + " - " + product.getProductExpireDate());
            }
        }
    }
}