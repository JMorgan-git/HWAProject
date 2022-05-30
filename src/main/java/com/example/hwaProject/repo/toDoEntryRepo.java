package com.example.hwaProject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hwaProject.domain.toDoEntry;

@Repository
public interface toDoEntryRepo extends JpaRepository<toDoEntry, Long>{
	
	List<toDoEntry> findByName(String name);

}
