package com.ysf.pm.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysf.pm.dtos.ProjectDto;
import com.ysf.pm.exceptions.ProjectNotFoundException;
import com.ysf.pm.models.TaskEntity;
import com.ysf.pm.models.UserEntity;
import com.ysf.pm.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectRestController {
	
	private final ProjectService projectService;
	
	@GetMapping("/projects/{projectId}")
	public ProjectDto findByid(@PathVariable String projectId) throws ProjectNotFoundException {
		return projectService.findByid(projectId);
	}
	@GetMapping("/projects")
	public List<ProjectDto> findAll(){
		return projectService.findAll();
	}
	
	@PostMapping("/projects")
	public ProjectDto save(@RequestBody ProjectDto projectDto) {
		return projectService.save(projectDto);
	}
	@DeleteMapping("/projects/{projetId}")
	public void deleteByid(@PathVariable String projectId) throws ProjectNotFoundException {
		projectService.deleteByid(projectId);
	}
	@PutMapping("/projects")
	public ProjectDto update(ProjectDto projectDto) throws ProjectNotFoundException {
		return projectService.update(projectDto);
	}
	@PostMapping("/projects/addmemberto/{projectId}")
	public ProjectDto addTeamMember(@PathVariable String projectId,@RequestBody UserEntity userEntity) throws ProjectNotFoundException {
		return projectService.addTeamMember(projectId, userEntity);
	}
	@PostMapping("/projects/removefrom/{projectId}")
	public ProjectDto removeTeamMember(@PathVariable String projectId,@RequestBody String userId) throws ProjectNotFoundException {
		return projectService.removeTeamMember(projectId, userId);
	}
	@PostMapping("projects/addtaskto/{projectId}")
	public ProjectDto addTask(@PathVariable String projectId,@RequestBody TaskEntity taskEntity) throws ProjectNotFoundException {
		return projectService.addTask(projectId, taskEntity);
	}
	@PostMapping("/projects/removetaskfrom/{projectId}")
	public ProjectDto removeTask(@PathVariable String projectId,@RequestBody Long taskId) throws ProjectNotFoundException {
		return projectService.removeTask(projectId, taskId);
	}
	
}
