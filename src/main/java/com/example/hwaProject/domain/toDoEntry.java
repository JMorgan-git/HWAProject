package com.example.hwaProject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class toDoEntry {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String entry;
	
	@Column(nullable = false)
	private long startTime;
	
	@Column(nullable = false)
	private long expectedDuration;
	
	public toDoEntry(String name, String entry, long startTime, long expectedDuration) {
		super();
		this.name = name;
		this.entry = entry;
		this.startTime = startTime;
		this.expectedDuration = expectedDuration;
	}


}
