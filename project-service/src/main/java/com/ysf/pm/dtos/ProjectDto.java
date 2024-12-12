package com.ysf.pm.dtos;

import java.util.Date;
import java.util.List;

import com.ysf.pm.models.TaskEntity;
import com.ysf.pm.models.UserEntity;

import lombok.Data;

@Data
public class ProjectDto {
	
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
