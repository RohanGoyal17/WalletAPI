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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.http.HttpStatus;


import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String expected = "[{\"transactionid\":6,\"senderphone\":999998888,\"receiverphone\":999998887,\"amount\":100}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testPost() throws Exception {
        String mockT = "{\"senderphone\":999998888,\"receiverphone\":999998887,\"amount\":1}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transaction").accept(MediaType.APPLICATION_JSON).content(mockT).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(200, response.getStatus());

        //JSONAssert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

}