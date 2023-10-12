package com.example.graaltasks.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import com.example.graaltasks.model.Task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TaskRepository implements InitializingBean{

	private Map<Long, Task> taskRepository;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.taskRepository = new HashMap<>();
		log.info("Repo created");
	}

	public Task save(Task task) {
		this.taskRepository.put(task.getId(), task);
		log.info("Task added");
		log.info(taskRepository.get(task.getId()).toString());
		return task;
	}
	
	public Task findById(Long id) {
		return Optional.ofNullable(taskRepository.
				get(id)).
				orElseThrow(RuntimeException::new);
	}
}
