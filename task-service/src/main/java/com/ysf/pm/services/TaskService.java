package com.ysf.pm.services;

import java.util.List;

import com.ysf.pm.dtos.TaskDto;
import com.ysf.pm.exceptions.TaskNotFoundException;

public interface TaskService {
	
	
	public TaskDto findByid(Long taskId) throws TaskNotFoundException;
	
	public TaskDto save(TaskDto taskDto);
	
	public List<TaskDto> findAll();
	
	public void deleteByid(Long taskId) throws TaskNotFoundException;
	
	public TaskDto update(TaskDto taskDto) throws TaskNotFoundException;
	
	public TaskDto markAsDone(Long LongId) throws TaskNotFoundException ;
	

    TaskDto assignedTo(String userId, Long taskId) throws TaskNotFoundException;

	

	List<TaskDto> findAllByids(List<Long> ids);
	
	

}
