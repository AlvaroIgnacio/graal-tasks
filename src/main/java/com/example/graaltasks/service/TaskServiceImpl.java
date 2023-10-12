package com.example.graaltasks.service;

import org.springframework.stereotype.Service;

import com.example.graaltasks.model.Task;
import com.example.graaltasks.repository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService{

	private TaskRepository taskRepository;
	
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Task findById(Long id) {
		log.info("Looking for task {id}", id);
		return this.taskRepository.findById(id);
	}

	@Override
	public Task save(Task task) {
		log.info("Saving task {}", task);
		return this.taskRepository.save(task);
	}
	
	

}
