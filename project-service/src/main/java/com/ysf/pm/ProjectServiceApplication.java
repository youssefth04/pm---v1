package com.ysf.pm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.ysf.pm.dtos.ProjectDto;
import com.ysf.pm.feign.TaskRestClient;
import com.ysf.pm.feign.UserRestClient;
import com.ysf.pm.models.TaskEntity;
import com.ysf.pm.models.UserEntity;
import com.ysf.pm.services.ProjectService;

@SpringBootApplication
@EnableFeignClients
public class ProjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(ProjectService projectService , UserRestClient userRestClient ,TaskRestClient taskRestClient) {
		
		List<UserEntity> users = userRestClient.findAllUsers();
		List<TaskEntity> tasks = taskRestClient.findAllTask();
		
		return args->{
			Stream.of("project1","project2","project3","project4").forEach(p->{
				ProjectDto projectDto= new ProjectDto();
				projectDto.setDescription(p+" ?????????");
				projectDto.setStartDate(new Date());
				projectDto.setEndDate(new Date());
				projectDto.setId(UUID.randomUUID().toString());
				projectDto.setName(p);
				projectDto.setListOfTasks(tasks);
				projectDto.setListOfUsers(users);
				users.forEach(u->{
					String userId = u.getId();
					List<String> listUserIds = projectDto.getUserIds();
					if(listUserIds==null) {
						listUserIds=new ArrayList<>();
						listUserIds.add(userId);
						projectDto.setUserIds(listUserIds);
					}
					listUserIds.add(userId);
					projectDto.setUserIds(listUserIds);
					
				});
				tasks.forEach(t->{
					Long tasksIds = t.getId();
					List<Long> listTaskIds=projectDto.getTaskIds();
					if(listTaskIds==null) {
						listTaskIds= new ArrayList<>();
						listTaskIds.add(tasksIds);
						projectDto.setTaskIds(listTaskIds);
					}
					listTaskIds.add(tasksIds);
					projectDto.setTaskIds(listTaskIds);
				});
				
				
				projectService.save(projectDto);
			});
			
		};
	}

}