package com.sema4.foudastore;

import net.bytebuddy.matcher.StringMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FoudaStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientsRestControllerTest {


    private static final String CLIENT_JSON = "{\"id\":1,\"firstname\":\"Jhon\",\"lastname\":\"DOE\",\"email\":\"jd@gmail.com\"}";

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void test401() throws Exception {
        mvc.perform(get("/clients/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(401));
        mvc.perform(post("/clients/", "{}").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(401));
    }

    @Test
    @Order(2)
    public void testFindAllClients200() throws Exception {
        mvc.perform(get("/clients/").header(AUTH_HEADER_NAME, AUTH_HEADER_VAL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void testSaveClient200() throws Exception {
        mvc.perform(post("/clients/")
                        .header(AUTH_HEADER_NAME, AUTH_HEADER_VAL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CLIENT_JSON))
                .andExpect(status().isOk());
        ;
    }

    @Test
    @Order(4)
    public void testfindClientById200() throws Exception {
        mvc.perform(get("/clients/1")
                        .header(AUTH_HEADER_NAME, AUTH_HEADER_VAL)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client.id", CoreMatchers.is(1)))
                .andExpect(jsonPath("$.client.firstname", CoreMatchers.is("Jhon")))
                .andExpect(jsonPath("$.client.lastname", CoreMatchers.is("DOE")))
                .andExpect(jsonPath("$.client.email", CoreMatchers.is("jd@gmail.com")))
        ;
    }




}
