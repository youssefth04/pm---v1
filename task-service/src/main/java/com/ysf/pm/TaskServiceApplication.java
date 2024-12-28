package com.ysf.pm;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.ysf.pm.dtos.TaskDto;
import com.ysf.pm.enums.TaskStatus;
import com.ysf.pm.feign.UserRestClient;
import com.ysf.pm.mappers.TaskMapper;
import com.ysf.pm.models.UserEntity;
import com.ysf.pm.services.TaskService;

@SpringBootApplication
@EnableFeignClients
public class TaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(TaskService taskService  , TaskMapper taskMapper) {
		return args->{
			 
			// List<UserEntity> users = userRestClient.findAllUsers();
				 Stream.of("task1","task2","task3").forEach(t->{
						TaskDto taskDto= new TaskDto();
						taskDto.setDescription(t+" blalablab");
						taskDto.setTaskStatus(TaskStatus.IN_PROGRESS);
						taskDto.setTitle(t);
						taskService.save(taskDto);
					});
				 
		};
	}

}
