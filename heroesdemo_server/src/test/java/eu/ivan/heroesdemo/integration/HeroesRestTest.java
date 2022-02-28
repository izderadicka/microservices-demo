package eu.ivan.heroesdemo.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.ivan.heroesdemo.entity.Hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class HeroesRestTest {
	
	@Autowired
	MockMvc rest;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void testSearch() throws Exception {
		String searchTerm = "ma";
		
		MvcResult result =  rest.perform(get("/api/heroes").param("name", searchTerm))
			.andExpect(status().isOk())
			.andReturn();
		
		String data = result.getResponse().getContentAsString();
		ArrayList<Hero> heroes = objectMapper.readValue(data, new TypeReference<ArrayList<Hero>>() {});
		
		assertEquals(4, heroes.size());
		
		assertTrue(heroes.stream().allMatch(h -> h.getName().toLowerCase().contains(searchTerm)));
		
	}
	
	

}
