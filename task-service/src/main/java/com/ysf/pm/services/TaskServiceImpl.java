package com.ysf.pm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ysf.pm.dtos.TaskDto;
import com.ysf.pm.enums.TaskStatus;
import com.ysf.pm.exceptions.TaskNotFoundException;
import com.ysf.pm.feign.NotificationRestClient;
import com.ysf.pm.feign.ProjectRestclient;
import com.ysf.pm.feign.UserRestClient;
import com.ysf.pm.mappers.TaskMapper;
import com.ysf.pm.models.NotificationEntity;
import com.ysf.pm.models.ProjectEntity;
import com.ysf.pm.models.UserEntity;
import com.ysf.pm.repositories.TaskRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

	private final TaskMapper taskMapper;
	private final TaskRepository taskRepository;
	private final UserRestClient userRestClient;
	private final NotificationRestClient notificationRestClient;
	private final ProjectRestclient projectRestclient;
	@Override
	public TaskDto findByid(Long taskId) throws TaskNotFoundException {
		
		TaskDto taskDto=taskMapper.toDto( taskRepository.findById(taskId).orElseThrow(()->
		new TaskNotFoundException("task not found")
				));
		UserEntity userEntity= userRestClient.findUserById(taskDto.getUserId());
		if(userEntity!=null) {
			taskDto.setUserEntity(userEntity);
			return taskDto;
		}
		else return taskDto;
		
		
	}
	@Override
	public TaskDto save(TaskDto taskDto) {
	
		return taskMapper.toDto( taskRepository.save(taskMapper.toEntity(taskDto)));
	}
	@Override
	public List<TaskDto> findAll() {
		
		List<TaskDto> listOfTasks=taskRepository.findAll().stream().map(taskMapper::toDto).collect(Collectors.toList());
		listOfTasks.forEach(t->{
			UserEntity userEntity= userRestClient.findUserById(t.getUserId());
			t.setUserEntity(userEntity);
		});
		return listOfTasks;
	}
	@Override
	public void deleteByid(Long taskId) throws TaskNotFoundException {
		if(findByid(taskId)!=null) {
			taskRepository.deleteById(taskId);
		}
		else throw new TaskNotFoundException("task not found");
		
	}
	@Override
	public TaskDto update(TaskDto taskDto) throws TaskNotFoundException {
		if(findByid(taskDto.getId())!=null) {
			return taskMapper.toDto( taskRepository.save(taskMapper.toEntity(taskDto)));
		}
		
		throw new TaskNotFoundException("task not found");
	}
	@Override
	public TaskDto markAsDone(Long LongId) throws TaskNotFoundException {
		TaskDto taskDto = findByid(LongId) ;
		ProjectEntity projectEntity= projectRestclient.findProjectbyid(taskDto.getProjectId());
		
		if(taskDto!=null) {
			taskDto.setTaskStatus(TaskStatus.DONE);
			taskRepository.save(taskMapper.toEntity(taskDto));
			projectEntity.getListOfUsers().forEach(u->{
				if(u.getRole().toString().equals("OWNER")) {
					NotificationEntity notificationEntity=new NotificationEntity();
					notificationEntity.setUserId(u.getId());
					notificationEntity.setUserEntity(u);
					notificationEntity.setMessage(taskDto.getUserEntity().getNom()+" just finished his work (task) "+taskDto.getTitle()+" : "+taskDto.getDescription());
					notificationRestClient.addNewNotification(notificationEntity);
				}
			});
			return taskDto;
		
		}
		else throw new TaskNotFoundException("task not found ");
		
	}
	@Override
	public TaskDto assignedTo(String userId,Long taskId) throws TaskNotFoundException {
		UserEntity userEntity=userRestClient.findUserById(userId);
		NotificationEntity notificationEntity=new NotificationEntity();
		
		TaskDto taskDto= findByid(taskId);
		if(userEntity!=null && taskDto!=null) {
			taskDto.setUserId(userId);
			taskDto.setUserEntity(userEntity);
			taskRepository.save(taskMapper.toEntity(taskDto));
			notificationEntity.setUserId(userId);
			notificationEntity.setUserEntity(userEntity);
			notificationEntity.setMessage(userEntity.getNom()+" you have new task to do "+taskDto.getTitle()+" : "+taskDto.getDescription());
			notificationRestClient.addNewNotification(notificationEntity);
			return taskDto;
			
		}
	
		else throw new TaskNotFoundException("task not found");
		
	}
	@Override
	public List<TaskDto> findAllByids(List<Long> ids){
		
		
		List<TaskDto> tasks = taskRepository.findAllById(ids).stream().map(taskMapper::toDto).collect(Collectors.toList());
		tasks.forEach(t->{
			t.setUserEntity(userRestClient.findUserById(t.getUserId()));
		});
		return tasks;
	}
	

	
	
	
}
