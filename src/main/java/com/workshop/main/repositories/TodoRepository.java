package com.workshop.main.repositories;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
 
import com.workshop.main.model.Todo;
 
public interface TodoRepository extends MongoRepository<Todo, String> {
	public List<Todo> findAll();
	public Todo findOne(String id);
	@SuppressWarnings("unchecked")
	public Todo save(Todo todo);
	public void delete(Todo todo);
}