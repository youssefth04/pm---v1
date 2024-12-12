package com.ysf.pm.dtos;

import com.ysf.pm.enums.TaskStatus;
import com.ysf.pm.models.UserEntity;

import lombok.Data;

@Data
public class TaskDto {

	
	private Long id;
	private String title;
	private String description;
	private TaskStatus taskStatus;
	
	private String userId;
	
	private String projectId;

	private UserEntity userEntity;
}
