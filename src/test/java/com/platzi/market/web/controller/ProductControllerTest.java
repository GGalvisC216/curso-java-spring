package com.platzi.market.web.controller;

import com.platzi.market.domain.Category;
import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import com.platzi.market.domain.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mvc;

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
    void getAll() throws Exception {
        when(productService.getAll()).thenReturn(products);
        mvc.perform(
                get("/products")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].name").exists())
                    .andExpect(jsonPath("$[0].active", equalTo(true)));
    }
}