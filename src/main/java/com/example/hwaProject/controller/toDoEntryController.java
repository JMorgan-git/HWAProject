package com.example.hwaProject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hwaProject.domain.toDoEntry;
import com.example.hwaProject.services.toDoService;

@RestController
@RequestMapping("/entries")
public class toDoEntryController {

	private toDoService service;

	public toDoEntryController(toDoService service) {
		this.service = service;
	}

	// Create - Post Request
	@PostMapping("/create")
	public ResponseEntity<toDoEntry> create(@RequestBody toDoEntry toEntry) {
		return new ResponseEntity<toDoEntry>(service.create(toEntry),HttpStatus.CREATED);
	}

	// read all
	@GetMapping("/getAll")
	public ResponseEntity<List<toDoEntry> >getAll() {
		return new ResponseEntity<List<toDoEntry>>(service.getAll(),HttpStatus.OK);
	}

	// read by id
	@GetMapping("/getById/{id}")
	public ResponseEntity<toDoEntry> getById(@PathVariable long id) {
		return new ResponseEntity<toDoEntry>(service.getById(id), HttpStatus.OK);
	}
	
	// read by name
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<toDoEntry>> getByName(@PathVariable String name) {
		return new ResponseEntity<List<toDoEntry>>(service.getByName(name), HttpStatus.OK);
	}

	// Update - Put/Patch Request
	@PutMapping("/update/{id}")
	public ResponseEntity<toDoEntry> update(@PathVariable long id, @RequestBody toDoEntry toEntry) {
		return new ResponseEntity<toDoEntry>(service.update(id, toEntry), HttpStatus.ACCEPTED);
	}

	// Delete - Delete Request
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return new ResponseEntity<Boolean>(service.delete(id), HttpStatus.NO_CONTENT);
	}
}
