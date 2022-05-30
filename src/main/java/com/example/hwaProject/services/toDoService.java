package com.example.hwaProject.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.hwaProject.domain.toDoEntry;
import com.example.hwaProject.repo.toDoEntryRepo;

@Service
public class toDoService {
	
	private toDoEntryRepo repo;
	
	public toDoService(toDoEntryRepo repo) {
		this.repo = repo;
	}
	
	// Create
	public toDoEntry create(toDoEntry toEntry) {
		return repo.saveAndFlush(toEntry);
	}

	// Read all
	public List<toDoEntry> getAll() {
		return repo.findAll();
	}

	// Read by id
	public toDoEntry getById(long entryId) {
		return repo.findById(entryId).get();
		//return repo.findById(id).orElseThrow(UserNotFoundException::new);
		//return repo.findById(id).orElseThrow(() -> new UserNotFoundExceptionWithID(id));
	}
	
	// Read by Name custom
	public List<toDoEntry> getByName(String name) {
		return repo.findByName(name);
	}

	// Update
	public toDoEntry update(long entryId, toDoEntry toEntry) {
		toDoEntry existing = repo.findById(entryId).get();
		existing.setName(toEntry.getName());
		existing.setEntry(toEntry.getEntry());
		existing.setStartTime(toEntry.getStartTime());
		existing.setExpectedDuration(toEntry.getExpectedDuration());
		return repo.saveAndFlush(existing);
		
	}

	// Delete
	public boolean delete(long entryId) {
		repo.deleteById(entryId);
		return repo.existsById(entryId);
	}
}
