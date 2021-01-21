package com.rest.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
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
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions?userId=test&page=0&size=1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "[{\"transactionid\":1,\"senderphone\":999998887,\"receiverphone\":999998888,\"amount\":120}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

}