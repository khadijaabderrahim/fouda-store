package com.sema4.foudastore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sema4.foudastore.dto.CreateOrderRequest;
import com.sema4.foudastore.dto.UpdateOrderStatusRequest;
import com.sema4.foudastore.entities.Client;
import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.entities.Status;
import com.sema4.foudastore.repositories.ClientRepository;
import com.sema4.foudastore.repositories.OrderRepository;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static com.sema4.foudastore.TestConstants.AUTH_HEADER_NAME;
import static com.sema4.foudastore.TestConstants.AUTH_HEADER_VAL;
import static com.sema4.foudastore.entities.Status.DELIVERED;
import static com.sema4.foudastore.entities.Status.FORWARDED;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Autowired
    private OrderRepository orderRepository;

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
    @org.junit.jupiter.api.Order(1)
    public void testSaveOrder200() throws Exception {
        LOGGER.info("test save order ");
        CreateOrderRequest request = new CreateOrderRequest();
        request.setClientId(1L);
        request.setSelectedProducts(List.of(1L, 2L));

        mvc.perform(post(ORDERS_SERVICE_BASE_URL).contentType(MediaType.APPLICATION_JSON).header(AUTH_HEADER_NAME, AUTH_HEADER_VAL)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().is(200));

        Optional<Order> order = orderRepository.findById(1L);

        assertTrue(order.isPresent());
        assertEquals(order.get().getStatus(), Status.NEW);
        assertEquals(request.getClientId(), order.get().getClient().getId());

    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void testFindAllOrders200() throws Exception {
        mvc.perform(get(ORDERS_SERVICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_HEADER_NAME, AUTH_HEADER_VAL)
                ).andExpect(status().is(200));
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void testUpdateOrderStatus200() throws Exception {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setClientId(1L);
        createOrderRequest.setSelectedProducts(List.of(1L, 2L));

        mvc.perform(post(ORDERS_SERVICE_BASE_URL).contentType(MediaType.APPLICATION_JSON).header(AUTH_HEADER_NAME, AUTH_HEADER_VAL)
                .content(objectMapper.writeValueAsString(createOrderRequest))).andExpect(status().is(200));


        UpdateOrderStatusRequest updateOrderStatusRequest = new UpdateOrderStatusRequest();
        updateOrderStatusRequest.setOrderId(1L);
        updateOrderStatusRequest.setStatus(FORWARDED);
        mvc.perform(post(ORDERS_SERVICE_BASE_URL+"/status")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateOrderStatusRequest))
                .header(AUTH_HEADER_NAME, AUTH_HEADER_VAL)
        ).andExpect(status().is(200));

        Optional<Order> order = orderRepository.findById(1L);
        assertTrue(order.isPresent());
        assertEquals(FORWARDED,order.get().getStatus());
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    public void testUpdateOrderStatusOrderDoesNotExist404() throws Exception {
        UpdateOrderStatusRequest updateOrderStatusRequest = new UpdateOrderStatusRequest();
        updateOrderStatusRequest.setOrderId(6969L);
        updateOrderStatusRequest.setStatus(FORWARDED);
        mvc.perform(post(ORDERS_SERVICE_BASE_URL+"/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateOrderStatusRequest))
                .header(AUTH_HEADER_NAME, AUTH_HEADER_VAL)
        ).andExpect(status().is(404))
                .andExpect(jsonPath("$.message" , is("Order 6969 not found")));

    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void testUpdateOrderStatusBadNewStatus400() throws Exception {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setClientId(2L);
        createOrderRequest.setSelectedProducts(List.of(1L, 2L, 2L));

        String responseBody = mvc.perform(post(ORDERS_SERVICE_BASE_URL).contentType(MediaType.APPLICATION_JSON).header(AUTH_HEADER_NAME, AUTH_HEADER_VAL)
                .content(objectMapper.writeValueAsString(createOrderRequest))).andExpect(status().is(200)).andReturn().getResponse().getContentAsString();

        Order order = objectMapper.readValue(responseBody,Order.class);
        assertNotNull(order);

        UpdateOrderStatusRequest updateOrderStatusRequest = new UpdateOrderStatusRequest();
        updateOrderStatusRequest.setOrderId(order.getId());
        updateOrderStatusRequest.setStatus(DELIVERED);

        responseBody = mvc.perform(post(ORDERS_SERVICE_BASE_URL+"/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateOrderStatusRequest))
                .header(AUTH_HEADER_NAME, AUTH_HEADER_VAL)
        ).andExpect(status().is(400))
                .andReturn()
                .getResponse()
                .getContentAsString();


        }


}
