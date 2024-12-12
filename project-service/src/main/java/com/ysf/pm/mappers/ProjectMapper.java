package com.ysf.pm.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ysf.pm.dtos.ProjectDto;
import com.ysf.pm.entities.ProjectEntity;

@Component
public class ProjectMapper {

	public ProjectEntity toEntity(ProjectDto projectDto) {
		ProjectEntity projectEntity= new ProjectEntity();
		BeanUtils.copyProperties(projectDto, projectEntity);
		return projectEntity;
	}
	
	public ProjectDto toDto(ProjectEntity projectEntity) {
		ProjectDto projectDto= new ProjectDto();
		BeanUtils.copyProperties(projectEntity, projectDto);
		return projectDto;
	}
}
