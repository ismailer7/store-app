package com.ur.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.ur"}) 
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHi() throws Exception {
		this.mockMvc.perform(get("/hi")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Hello over there!")));
	}
	
	@Test
	public void testUnauthorizedPoint() throws Exception {
		this.mockMvc.perform(get("/something")).andDo(print()).andExpect(status().isUnauthorized());
	}
	
}
