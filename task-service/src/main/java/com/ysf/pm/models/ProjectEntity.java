package com.ysf.pm.models;

import java.util.Date;
import java.util.List;

import com.ysf.pm.entities.TaskEntity;

import lombok.Data;

@Data
public class ProjectEntity {
	
	private String id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private List<String> userIds;
	private List <Long> taskIds;
	private List<TaskEntity> listOfTasks;
	
	private List<UserEntity> listOfUsers;
	

}
