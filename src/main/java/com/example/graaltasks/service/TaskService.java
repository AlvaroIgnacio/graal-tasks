package com.example.graaltasks.service;

import com.example.graaltasks.model.Task;

public interface TaskService {

	Task findById(Long id);
	Task save(Task task);

}
