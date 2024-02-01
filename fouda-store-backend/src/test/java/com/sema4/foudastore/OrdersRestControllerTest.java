package com.sema4.foudastore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sema4.foudastore.dto.CreateOrderRequest;
import com.sema4.foudastore.entities.Client;
import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.repositories.ClientRepository;
import com.sema4.foudastore.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.sema4.foudastore.TestConstants.AUTH_HEADER_NAME;
import static com.sema4.foudastore.TestConstants.AUTH_HEADER_VAL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FoudaStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdersRestControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersRestControllerTest.class);

    private static final String ORDERS_SERVICE_BASE_URL = "/orders/";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void initData() {
        LOGGER.info("Init Data for test");
        LOGGER.info("Init Clients");

        Client client1 = new Client("jhon", "dow", "jd@test.com");
        Client client2 = new Client("jane", "smith", "jt@test.com");
        clientRepository.save(client1);
        clientRepository.save(client2);

        LOGGER.info("Init Products");
        Product product1 = new Product("product 1 label", "product 1 description", 99.99, 9, "image.url.jpg");
        Product product2 = new Product("product 2 label", "product 2 description", 11.0, 2, "image2.url.jpg");
        productRepository.save(product1);
        productRepository.save(product2);

    }

    @Test
    public void testSaveOrder200() throws Exception {
        LOGGER.info("test save order ");
        CreateOrderRequest request = new CreateOrderRequest();
        request.setClientId(1L);
        request.setSelectedProducts(List.of(1L,2L));

        mvc.perform(post(ORDERS_SERVICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_HEADER_NAME,AUTH_HEADER_VAL)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is(200));

    }

    @Test
    public void testFindAllOrders200() {
        LOGGER.info("test save order ");
    }

}
