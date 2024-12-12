package com.ysf.pm.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ysf.pm.models.TaskEntity;

@FeignClient("task-service")
public interface TaskRestClient {
	
	@GetMapping("/api/tasks/{taskId}")
	public TaskEntity findTaskByid(@PathVariable Long taskId);
	@GetMapping("/api/tasks/alltasksbyids")
	public List<TaskEntity> findAllByids(@RequestParam List<Long> ids);
	@PostMapping("/api/tasks")
	public TaskEntity save(@RequestBody TaskEntity taskEntity);
	
	
	// pour tester et remplire la base de données
	@GetMapping("/api/tasks")
	public List<TaskEntity> findAllTask();
}
