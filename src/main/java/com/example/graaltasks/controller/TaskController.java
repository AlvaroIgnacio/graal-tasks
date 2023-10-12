package com.example.graaltasks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.graaltasks.model.Task;
import com.example.graaltasks.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/ping")
	public String pong() {
		return "pong";
	}
	
	@PostMapping
	public ResponseEntity<Task> save(@RequestBody Task task) {
		return ResponseEntity.ok(this.taskService.save(task));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> find(@PathVariable Long id){
		return ResponseEntity.ok(this.taskService.findById(id));
	}
}
