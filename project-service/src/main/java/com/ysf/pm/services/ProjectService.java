package com.ysf.pm.services;

import java.util.List;

import com.ysf.pm.dtos.ProjectDto;
import com.ysf.pm.exceptions.ProjectNotFoundException;
import com.ysf.pm.models.TaskEntity;
import com.ysf.pm.models.UserEntity;

public interface ProjectService {
	
	public ProjectDto save(ProjectDto projectDto);
	
	List<ProjectDto> findAll();
	
	void deleteByid(String projectId) throws ProjectNotFoundException;
	
	ProjectDto update(ProjectDto projectDto) throws ProjectNotFoundException;
	
	ProjectDto findByid(String projectId) throws ProjectNotFoundException;
	
	ProjectDto addTask(String projectId,TaskEntity taskEntity) throws ProjectNotFoundException ;
	
	ProjectDto removeTask(String projectId,Long taskId) throws ProjectNotFoundException;
	
	ProjectDto addTeamMember(String projectId,UserEntity userEntity) throws ProjectNotFoundException;
	
	ProjectDto removeTeamMember(String projectId,String userId) throws ProjectNotFoundException;
}
