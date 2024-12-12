package com.ysf.pm.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ysf.pm.dtos.TaskDto;
import com.ysf.pm.entities.TaskEntity;

@Component
public class TaskMapper {
	
		
	public TaskDto toDto(TaskEntity taskEntity) {
		TaskDto taskDto = new TaskDto();
		BeanUtils.copyProperties(taskEntity	, taskDto);
		taskDto.setUserEntity(taskEntity.getUserEntity());
		return taskDto;
	}
	public TaskEntity toEntity(TaskDto taskDto) {
		TaskEntity taskEntity =new TaskEntity();
		BeanUtils.copyProperties(taskDto, taskEntity);
		taskEntity.setUserEntity(taskDto.getUserEntity());
		return taskEntity;
	}
		
}
