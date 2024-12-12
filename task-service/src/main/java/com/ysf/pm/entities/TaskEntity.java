package com.ysf.pm.entities;

import com.ysf.pm.enums.TaskStatus;
import com.ysf.pm.models.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class TaskEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;
	
	private String projectId;
	
	private String userId;
	@Transient
	private UserEntity userEntity;
	

}
