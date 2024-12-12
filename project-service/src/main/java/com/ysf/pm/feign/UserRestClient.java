package com.ysf.pm.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ysf.pm.models.UserEntity;

@FeignClient("user-service")
public interface UserRestClient {
	
	@GetMapping("/api/users/{userId}")
	public UserEntity findUserByid(@PathVariable String userId);
	
	@GetMapping("/api/users/allbyids")
	public List<UserEntity> findAllByids(@RequestParam Iterable<String> ids);
	
	@GetMapping("/api/users")
	public List<UserEntity> findAllUsers();


}
