package com.ysf.pm.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ysf.pm.dtos.TaskDto;
import com.ysf.pm.exceptions.TaskNotFoundException;
import com.ysf.pm.services.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskRestController {
	
	private final TaskService taskService;
	
	@GetMapping("/tasks/{taskId}")
	public TaskDto findByid(@PathVariable Long taskId) throws TaskNotFoundException {
		return taskService.findByid(taskId);
	}
	@GetMapping("/tasks")
	public List<TaskDto> findAll() {
		return taskService.findAll();
	}
	@DeleteMapping("/tasks/{taskid}")
	public void deleteByid(@PathVariable Long taskid) throws TaskNotFoundException {
		taskService.deleteByid(taskid);
	}
	@PutMapping("/tasks")
	public TaskDto update(@RequestBody TaskDto taskDto) throws TaskNotFoundException {
		return taskService.update(taskDto);
	}
	@PostMapping("/tasks")
	public TaskDto save(@RequestBody TaskDto taskDto) {
		return taskService.save(taskDto);
	}
	@PostMapping("/tasks/editStatus")
	public TaskDto markAsDone(@RequestBody Long taskId) throws TaskNotFoundException {
		return taskService.markAsDone(taskId);
	}
	
	@PostMapping("/tasks/assignTo/{taskId}")
	public TaskDto assignedTo(@RequestBody String userId,@PathVariable Long taskId) throws TaskNotFoundException {
		return taskService.assignedTo(userId, taskId);
		
	}
	@GetMapping("/tasks/alltasksbyids")
	public List<TaskDto> findAllByids(@RequestParam("ids") List<Long> ids){
		
		return taskService.findAllByids(ids);
	}
	

}
