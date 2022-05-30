package com.example.hwaProject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.hwaProject.domain.toDoEntry;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class toDoEntryControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void createTest() throws Exception {
		toDoEntry input = new toDoEntry("John", "Walk cat", 1130, 30);
		String inputAsJson = mapper.writeValueAsString(input);
		
		toDoEntry output = new toDoEntry(2L, "John", "Walk cat", 1130, 30);
		String outputAsJson = mapper.writeValueAsString(output);
		
		mvc.perform(post("/entries/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(inputAsJson))
			.andExpect(status().isCreated())
			.andExpect(content().json(outputAsJson));
	}
	
	@Test
	public void getAllTest() throws Exception {
		toDoEntry toEntry = new toDoEntry(1L, "John", "Walk dog", 1030, 30);
		List<toDoEntry> output = new ArrayList<>();
		output.add(toEntry);

		String outputAsJson = mapper.writeValueAsString(output);
		
		mvc.perform(get("/entries/getAll")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(outputAsJson));
	}
	
	@Test
	public void getByIdTest() throws Exception {
		toDoEntry entry = new toDoEntry(1L, "John", "Walk dog", 1030, 30);
		String outputAsJson = mapper.writeValueAsString(entry);
		
		mvc.perform(get("/entries/getById/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(outputAsJson));
	}
	
	@Test
	public void getByFirstNameTest() throws Exception {
		toDoEntry entry = new toDoEntry(1L, "John", "Walk dog", 1030, 30);
		List<toDoEntry> output = new ArrayList<>();
		output.add(entry);
		String outputAsJson = mapper.writeValueAsString(output);
		
		mvc.perform(get("/entries/getByName/John")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(outputAsJson));
	}
	
	@Test
	public void updateTest() throws Exception {
		toDoEntry entry = new toDoEntry("Jane", "Walk dog but better", 1030, 45);
		toDoEntry result = new toDoEntry(1L, "Jane", "Walk dog but better", 1030, 45);
		String entryAsJson = mapper.writeValueAsString(entry);
		String resultAsJson = mapper.writeValueAsString(result);
		
		mvc.perform(put("/entries/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(entryAsJson))
			.andExpect(status().isAccepted())
			.andExpect(content().json(resultAsJson));
	}
	
	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete("/entries/delete/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}
	
}
