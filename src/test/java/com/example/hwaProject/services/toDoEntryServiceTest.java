package com.example.hwaProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.hwaProject.domain.toDoEntry;
import com.example.hwaProject.repo.toDoEntryRepo;

@SpringBootTest
public class toDoEntryServiceTest {

	@Autowired
	private toDoService service;

	@MockBean
	private toDoEntryRepo repo;

	// Create Test
	@Test
	public void createTest() {
		toDoEntry input = new toDoEntry("John", "Walk dog", 1130, 30);
		toDoEntry output = new toDoEntry(1L, "John", "Walk dog", 1130, 30);

		Mockito.when(repo.saveAndFlush(output)).thenReturn(output);

		assertEquals(output, service.create(output));

		Mockito.verify(repo, Mockito.times(1)).saveAndFlush(output);
	}

	// Read all Test
	@Test
	public void getAllTest() {
		List<toDoEntry> output = new ArrayList<>();
		output.add(new toDoEntry(1L, "John", "Walk dog", 1130, 30));

		Mockito.when(repo.findAll()).thenReturn(output);

		assertEquals(output, service.getAll());

		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	// Read by id Test
	@Test
	public void getByIdTest() {

		toDoEntry output = new toDoEntry(1L, "John", "Walk dog", 1130, 30);

		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(output));

		assertEquals(output, service.getById(1L));

		Mockito.verify(repo, Mockito.times(1)).findById(1L);
	}

	// Read by Name custom Test
	@Test
	public void getByFirstNameTest() {
		List<toDoEntry> output = new ArrayList<>();
		output.add(new toDoEntry(1L, "John", "Walk dog", 1130, 30));

		Mockito.when(repo.findByName("John")).thenReturn(output);

		assertEquals(output, service.getByName("John"));

		Mockito.verify(repo, Mockito.times(1)).findByName("John");
	}

	// Update Test
	@Test
	public void updateTest() {

		toDoEntry input = new toDoEntry("Dave", "Hoover", 1000, 60);
		Optional<toDoEntry> existing = Optional.of(new toDoEntry(1L, "John", "Walk dog", 1130, 30));
		toDoEntry output = new toDoEntry(1L, "Dave", "Hoover", 1000, 60);

		Mockito.when(repo.findById(1L)).thenReturn(existing);
		Mockito.when(repo.saveAndFlush(output)).thenReturn(output);

		assertEquals(output, service.update(1L, input));

		Mockito.verify(repo, Mockito.times(1)).findById(1L);
		Mockito.verify(repo, Mockito.times(1)).saveAndFlush(output);
	}

	// Delete Test
	// @Disabled
	@Test
	public void deleteTest() {
		final long Id = 1L;
		Mockito.when(repo.existsById(Id)).thenReturn(true);

		assertEquals(true, service.delete(1L));

		Mockito.verify(repo, Mockito.times(1)).deleteById(Id);
	}

}
