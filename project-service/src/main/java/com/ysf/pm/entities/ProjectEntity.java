package com.ysf.pm.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.ysf.pm.models.TaskEntity;
import com.ysf.pm.models.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class ProjectEntity {
	@Id
	private String id;
	private String name;
	private String description;
	@CreatedDate
	private Date startDate;
	@LastModifiedDate
	private Date endDate;
	
	private List<String> userIds;
	
	private List<Long> taskIds;
	@Transient
	private List<TaskEntity> listOfTasks;
	@Transient
	private List<UserEntity> listOfUsers;
	

}
