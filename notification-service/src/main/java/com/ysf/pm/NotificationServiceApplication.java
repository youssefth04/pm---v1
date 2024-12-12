package com.ysf.pm;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.ysf.pm.dtos.NotificationDto;
import com.ysf.pm.feign.UserRestClient;
import com.ysf.pm.models.UserEntity;
import com.ysf.pm.services.NotificationService;

@SpringBootApplication
@EnableFeignClients
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(NotificationService notificationService,UserRestClient userRestClient) {
		return args->{
			List<UserEntity> users = userRestClient.findAll();
			
			users.forEach(u->{
				Stream.of("notification 1"," notif 2"," notif 3").forEach(n->{
					NotificationDto notificationDto = new  NotificationDto();
					notificationDto.setMessage(n);
					notificationDto.setUserEntity(u);
					notificationDto.setUserId(u.getId());
					notificationService.save(notificationDto);
				});
			});
			
		};
	}

}
