package com.workshop.main.web.api;

import java.util.List;

import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.workshop.main.model.Todo;
import com.workshop.main.repositories.TodoRepository;
 
@RestController
@RequestMapping("/api/todo")
public class TodoController {
	
	@Autowired
	TodoRepository todoRepository;
	
	@RequestMapping(value = "get",method=RequestMethod.GET)
	public List<Todo> getAllListTodo() {
		return todoRepository.findAll();
	}
	
	@RequestMapping(value="get/{id}", method=RequestMethod.GET)
	public ResponseEntity<Todo> getListTodoById(@PathVariable("id") String id) {
		Todo todo = todoRepository.findOne(id);
		if(todo == null) {
			return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Todo>(todo, HttpStatus.OK);
		}
	}
 
	@RequestMapping(value = "create",method=RequestMethod.POST)
	public String createTodo(@RequestBody Todo todo) {
		if (todo.getDetail() == null || todo.getDetail().isEmpty()){
			todo.setDetail("Nothing");
		}
		todoRepository.save(todo);
	    return "Created "+todo.getSubject();
	}
 
	@RequestMapping(value="updatestatus/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Todo> updateStatusTodoById(@RequestBody Todo todo, @PathVariable("id") String id) {
		Todo todoData = todoRepository.findOne(id);
		if(todoData == null) {
			return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
		}
		todoData.setStatus(todo.getStatus());
		Todo updatedTodo = todoRepository.save(todoData);
		return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
	}
	
	@RequestMapping(value="update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Todo> updateTodoById(@RequestBody Todo todo, @PathVariable("id") String id) {
		Todo todoData = todoRepository.findOne(id);
		if(todoData == null) {
			return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
		}
		todoData.setSubject(todo.getSubject());
		todoData.setDetail(todo.getDetail());
		Todo updatedTodo = todoRepository.save(todoData);
		return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
	}
	
	@RequestMapping(value="delete/{id}", method=RequestMethod.DELETE)
	public String deleteTodo(@PathVariable("id") String id) {
		Todo todo = todoRepository.findOne(id);
		todoRepository.delete(id);
		return "Delete "+todo.getSubject()+" Done";
	}
	
}
