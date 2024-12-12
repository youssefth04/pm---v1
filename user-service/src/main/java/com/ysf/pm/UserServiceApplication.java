package com.ysf.pm;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ysf.pm.entities.UserEntity;
import com.ysf.pm.enums.Roles;
import com.ysf.pm.mappers.UserMapper;
import com.ysf.pm.repositories.UserRepository;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository ) {
		return arg->{
			
			Stream.of("said","amine","sami").forEach(u->{
				UserEntity userEntity= new UserEntity();
				userEntity.setEmail(u+"@gmail.com");
				userEntity.setId(UUID.randomUUID().toString());
				userEntity.setRole(Math.random()>0.5?Roles.COLLABORATOR:Roles.OWNER);
				userEntity.setNom(u);
				userRepository.save(userEntity);
			});
			
		};
	}

}
