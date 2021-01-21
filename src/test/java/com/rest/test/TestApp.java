package com.rest.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MvcResult;
import org.skyscreamer.jsonassert.JSONAssert;

public class TestApp extends UserApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testTransaction() throws Exception {
        //mockMvc.perform(get("/transactions?userId=test&page=0&size=1")).andExpect(status().isOk())
                //.andExpect(content().contentType("application/json;charset=UTF-8"));
                //.andExpect(jsonPath("$.transactionid").value(1)).andExpect(jsonPath("$.senderphone").value(999998887))
                //.andExpect(jsonPath("$.receiverphone").value(999998888)).andExpect(jsonPath("$.amount").value(120));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions?userId=test&page=0&size=1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "[{\"transactionid\":1,\"senderphone\":999998887,\"receiverphone\":999998888,\"amount\":120}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

}