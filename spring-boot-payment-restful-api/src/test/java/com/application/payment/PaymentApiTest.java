package com.application.payment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PaymentApiTest {

	@InjectMocks
	private PaymentController controller;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void shouldPerformAPostRequest() throws Exception {
		MockHttpServletRequestBuilder post = MockMvcRequestBuilders
			.post("/your_uri")
			.param("firstParam", "param value")
			.param("secondParam", "param value")
			.accept(MediaType.APPLICATION_JSON)
			.header("user", "custom user header")
			.content("{\"user\": \"Alex\"}");

		mockMvc
			.perform(post);
	}
	
	@Test
	public void shouldReturnPaymentById() throws Exception {
		mockMvc.perform(get("http://localhost:8080/payments/1")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.equalTo("STARTED")));
	}
	
}
