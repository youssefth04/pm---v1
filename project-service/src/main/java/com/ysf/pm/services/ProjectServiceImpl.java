package com.ysf.pm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ysf.pm.dtos.ProjectDto;
import com.ysf.pm.exceptions.ProjectNotFoundException;
import com.ysf.pm.feign.TaskRestClient;
import com.ysf.pm.feign.UserRestClient;
import com.ysf.pm.mappers.ProjectMapper;
import com.ysf.pm.models.TaskEntity;
import com.ysf.pm.models.UserEntity;
import com.ysf.pm.repositories.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

	
	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;
	private final TaskRestClient taskRestClient;
	private final UserRestClient userRestClient;
	
	@Override
	public ProjectDto save(ProjectDto projectDto ) {
		return projectMapper.toDto( projectRepository.save(projectMapper.toEntity(projectDto)));
	}
	@Override
	public List<ProjectDto> findAll() {
		List<ProjectDto> projects= projectRepository.findAll().stream().map(projectMapper::toDto).collect(Collectors.toList());
		projects.forEach(p->{
			List<String> usersIds = p.getUserIds();
			List<Long> tasksIds = p.getTaskIds();
			
			if(usersIds!=null) {
				List<UserEntity>users = userRestClient.findAllByids(usersIds);
				p.setListOfUsers(users);
				
			}
			if(tasksIds!=null) {
				List<TaskEntity>tasks = taskRestClient.findAllByids(tasksIds);
				p.setListOfTasks(tasks);
			}
		});
		return projects;
	}
	@Override
	public void deleteByid(String projectId) throws ProjectNotFoundException {
		if(findByid(projectId)!=null) {
			projectRepository.deleteById(projectId);
		}
		else throw new ProjectNotFoundException("project not found");
	}
	@Override
	public ProjectDto update(ProjectDto projectDto) throws ProjectNotFoundException {
		ProjectDto project=findByid(projectDto.getId());
		if(project!=null) {
		return projectMapper.toDto(	projectRepository.save(projectMapper.toEntity(projectDto)));
		}
		else throw new ProjectNotFoundException("project not found");

	}
	
	
	
	
	@Override
	public ProjectDto findByid(String projectId) throws ProjectNotFoundException {
			ProjectDto projectDto=projectMapper.toDto( projectRepository.findById(projectId).orElseThrow( ()->
			new ProjectNotFoundException("project not found")
					) );
			if(projectDto!=null && ((projectDto.getUserIds().isEmpty()) || (projectDto.getTaskIds().isEmpty()))) {
			
				return projectDto;
			
			}
			else if(projectDto!=null && (!(projectDto.getUserIds().isEmpty()) || !(projectDto.getTaskIds().isEmpty()))){
				List<UserEntity> users=userRestClient.findAllByids(projectDto.getUserIds());
				projectDto.setListOfUsers(users);
				projectRepository.save(projectMapper.toEntity(projectDto));
				return projectDto;
				
			}
			return projectDto;
			
	}
	@Override
	public ProjectDto addTask(String projectId, TaskEntity taskEntity) throws ProjectNotFoundException {
		ProjectDto projectDto=findByid(projectId);
		if(projectDto!=null) {
			List<TaskEntity> tasks = taskRestClient.findAllByids( projectDto.getTaskIds());
			TaskEntity addedTask = taskRestClient.save(taskEntity);
			List<Long> tasksIds = projectDto.getTaskIds();
			tasksIds.add(addedTask.getId());
			tasks.add(addedTask);
			projectDto.setTaskIds(tasksIds);
			 projectDto.setListOfTasks(tasks);
			 projectRepository.save(projectMapper.toEntity(projectDto));
			 return projectDto;
		}
		
		else throw new ProjectNotFoundException("project not found");
	
	}
	@Override
	public ProjectDto removeTask(String projectId, Long taskId) throws ProjectNotFoundException {
		ProjectDto projectDto=findByid(projectId);
		TaskEntity task=taskRestClient.findTaskByid(taskId);
		List<TaskEntity> tasks= taskRestClient.findAllByids( projectDto.getTaskIds());
		tasks.remove(task);
		List<Long> tasksIds = projectDto.getTaskIds();
		tasksIds.remove(taskId);
		projectDto.setTaskIds(tasksIds);
		projectDto.setListOfTasks(tasks);
		projectRepository.save(projectMapper.toEntity(projectDto));
		return projectDto;
		
		
	}
	@Override
	public ProjectDto addTeamMember(String projectId, UserEntity userEntity) throws ProjectNotFoundException {
		ProjectDto projectDto=findByid(projectId);
		List<UserEntity> users=projectDto.getListOfUsers();
		List<String> usersIds=projectDto.getUserIds();
		if(users==null || usersIds==null) {
			users.set(0, userEntity);
			usersIds.set(0, userEntity.getId());
			projectRepository.save(projectMapper.toEntity(projectDto));
			return projectDto;
		}
		else {
		users.add(userEntity);
		usersIds.add(userEntity.getId());
		projectDto.setUserIds(usersIds);
		projectDto.setListOfUsers(users);
		projectRepository.save(projectMapper.toEntity(projectDto));
		return projectDto;}
	}
	@Override
	public ProjectDto removeTeamMember(String projectId,String userId) throws ProjectNotFoundException {
		ProjectDto projectDto=findByid(projectId);
		UserEntity userEntity=userRestClient.findUserByid(userId);
		List<UserEntity> users=projectDto.getListOfUsers();
		List<String> usersIds= projectDto.getUserIds();
		users.remove(userEntity);
		usersIds.remove(userId);
		projectDto.setUserIds(usersIds);
		projectDto.setListOfUsers(users);
		projectRepository.save(projectMapper.toEntity(projectDto));
		return projectDto;
	}
	@Override
	public List<ProjectDto> findAllProjectsByUserId(String userId) {
		
		List<ProjectDto> listOfProjects = projectRepository.findAllByUserId(userId).stream().map(projectMapper::toDto).collect(Collectors.toList());
		listOfProjects.forEach(p->{
			List<String> usersIds = p.getUserIds();
			List<Long> tasksIds = p.getTaskIds();
			
			if(usersIds!=null) {
				List<UserEntity>users = userRestClient.findAllByids(usersIds);
				p.setListOfUsers(users);
				
			}
			if(tasksIds!=null) {
				List<TaskEntity>tasks = taskRestClient.findAllByids(tasksIds);
				p.setListOfTasks(tasks);
			}
		});
		return listOfProjects;
		
	}
	
}
