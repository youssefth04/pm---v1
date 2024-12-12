package com.ysf.pm.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ysf.pm.models.UserEntity;

@FeignClient("user-service")
public interface UserRestClient {
	@GetMapping("/api/users/{userId}")
	 UserEntity findUserById(@PathVariable String userId);
	
	//pour le test et remplire l base de données
	
	@GetMapping("/api/users")
	public List<UserEntity> findAllUsers();
}
