package com.miksonx.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miksonx.model.Todo;
import com.miksonx.service.TodoService;

@RestController
public class TodoRestController {

	@Autowired
	TodoService service;
	
	@RequestMapping(path="/todos")
	public List<Todo> retreieveAllTodos(){
		return service.retrieveTodos("Miksonx");
		
	}
	
	@RequestMapping(path="/todos/{id}")
	public Todo retreieveTodos(@PathVariable int id){
		return service.retrieveTodo(id);
		
	}
}
