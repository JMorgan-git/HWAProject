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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.hwaProject.domain.toDoEntry;
import com.example.hwaProject.services.toDoService;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class toDoEntryControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private toDoService service;
	
	@Test
	public void createTest() throws Exception {
		toDoEntry input = new toDoEntry("John", "Walk dog", 1130, 30);
		String inputAsJson = mapper.writeValueAsString(input);
		toDoEntry output = new toDoEntry(2L, "John", "Walk dog", 1130, 30);
		String outputAsJson = mapper.writeValueAsString(output);
		
		Mockito.when(this.service.create(input)).thenReturn(output);
		
		mvc.perform(post("/entries/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(inputAsJson))
			.andExpect(status().isCreated())
			.andExpect(content().json(outputAsJson));
	}
	
	@Test
	public void getAllTest() throws Exception {
		toDoEntry toDoEntry = new toDoEntry(1L, "John", "Walk dog", 1030, 30);
		List<toDoEntry> output = new ArrayList<>();
		output.add(toDoEntry);
		String outputAsJson = mapper.writeValueAsString(output);
		
		Mockito.when(this.service.getAll()).thenReturn(output);
		
		mvc.perform(get("/entries/getAll")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(outputAsJson));
	}
	
	@Test
	public void getByIdTest() throws Exception {
		toDoEntry entry = new toDoEntry(1L, "John", "Walk dog", 1030, 30);
		String outputAsJson = mapper.writeValueAsString(entry);

		Mockito.when(this.service.getById(1L)).thenReturn(entry);
		
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

		Mockito.when(this.service.getByName("John")).thenReturn(output);
		
		mvc.perform(get("/entries/getByName/John")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(outputAsJson));
	}
	
	@Test
	public void updateTest() throws Exception {
		toDoEntry entry = new toDoEntry("John", "Walk dog", 1030, 30);
		toDoEntry result = new toDoEntry(1L, "Jane", "Walk dog but better", 1030, 45);
		String entryAsJson = mapper.writeValueAsString(entry);
		String resultAsJson = mapper.writeValueAsString(result);
		
		Mockito.when(this.service.update(1L, entry)).thenReturn(result);
		
		mvc.perform(put("/entries/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(entryAsJson))
			.andExpect(status().isAccepted())
			.andExpect(content().json(resultAsJson));
	}
	
	@Test
	public void deleteTest() throws Exception {
		Mockito.when(this.service.delete(1L)).thenReturn(true);
		
		mvc.perform(delete("/entries/delete/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}
	
}
