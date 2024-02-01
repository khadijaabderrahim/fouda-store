package com.sema4.foudastore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.sema4.foudastore.TestConstants.AUTH_HEADER_NAME;
import static com.sema4.foudastore.TestConstants.AUTH_HEADER_VAL;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FoudaStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductsRestControllerTest {


    private static final String PRODUCT_SERVICE_BASE_URL="/products/";

    private Product product1 = new Product("product 1 label","product 1 description" , 99.99,9,"image.url.jpg");
    private Product product2 = new Product("product 2 label","product 2 description" , 11.0,2,"image2.url.jpg");

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Order(1)
    public void testSaveProduct200() throws Exception {
        mvc.perform(post(PRODUCT_SERVICE_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_HEADER_NAME,AUTH_HEADER_VAL)
                        .content(objectMapper.writeValueAsString(product1)))
                .andExpect(status().is(200));

        mvc.perform(post(PRODUCT_SERVICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_HEADER_NAME,AUTH_HEADER_VAL)
                        .content(objectMapper.writeValueAsString(product2)))
                .andExpect(status().is(200));
    }



    @Test
    @Order(2)
    public void testFindAllProducts200() throws Exception {
        mvc.perform(get(PRODUCT_SERVICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_HEADER_NAME,AUTH_HEADER_VAL))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.length()", is(2)));

    }

    @Test
    @Order(3)
    public void testFindProductById200() throws Exception {
        mvc.perform(get(PRODUCT_SERVICE_BASE_URL+"/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_HEADER_NAME,AUTH_HEADER_VAL))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.label", is(product2.getLabel())))
                .andExpect(jsonPath("$.description", is(product2.getDescription())))
                .andExpect(jsonPath("$.prix", is(product2.getPrix())))
                .andExpect(jsonPath("$.rating", is(product2.getRating())))
                .andExpect(jsonPath("$.image", is(product2.getImage())))
        ;

    }

    @Test
    @Order(4)
    public void testDeleteProduct200() throws Exception {
        mvc.perform(delete(PRODUCT_SERVICE_BASE_URL+"/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_HEADER_NAME,AUTH_HEADER_VAL))
                .andExpect(status().is(200));

        assertEquals(1,productRepository.findAll().size(),"Problem in products delete");

    }


}
