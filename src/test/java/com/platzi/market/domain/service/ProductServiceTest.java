package com.platzi.market.domain.service;

import com.platzi.market.domain.Category;
import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    List<Product> products;

    @BeforeEach
    void setUp() {
        Category c = new Category();
        c.setCategoryId(1);
        c.setActive(true);
        c.setCategory("Frutas y verduras");

        Product p1 = new Product();
        p1.setProductId(1);
        p1.setName("Guayaba");
        p1.setCategoryId(1);
        p1.setPrice(3500);
        p1.setActive(true);
        p1.setCategory(c);

        products = Arrays.asList(p1);
    }

    @Test
    void getAll() {
        when(productRepository.getAll()).thenReturn(products);
        List<Product> productos = productService.getAll();
        assertEquals(productos.get(0).getProductId(), 1);
        assertEquals(productos.get(0).getName(), "Guayaba");
    }


    @Test
    void getProduct() {
        when(productRepository.getProduct(anyInt()))
                .thenReturn(Optional.of(products.get(0)));
        Optional<Product> product = productService.getProduct(1);
        assertEquals(product.get(),products.get(0));
    }
}