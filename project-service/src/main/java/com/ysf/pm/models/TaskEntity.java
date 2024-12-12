package com.ysf.pm.models;

import lombok.Data;

@Data
public class TaskEntity {
	
	
	private Long id;
	private String title;
	private String description;
	private TaskStatus taskStatus;
	
	private String userId;
	
	private UserEntity userEntity;

}
