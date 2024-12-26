package com.ysf.pm.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ysf.pm.dtos.UserDto;
import com.ysf.pm.exception.UserNotFoundException;
import com.ysf.pm.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserRestController {
	
	private final UserService userService;
	
	
	@GetMapping("/users")
	public List<UserDto> findAll() {
		return userService.findAll();
	}
	@PostMapping("/users")
	public UserDto save(@RequestBody UserDto userDto) {
		return userService.save(userDto);
	}
	@PutMapping("/users")
	public UserDto update(@RequestBody UserDto userDto) throws UserNotFoundException {
		return userService.update(userDto);
	}
	@GetMapping("/users/{userId}")
	public UserDto findByid(@PathVariable String userId) throws UserNotFoundException {
		return userService.findByid(userId);
		
	}
	@DeleteMapping("/users/{userId}")
	public void deleteByid(@PathVariable String userId) throws UserNotFoundException {
		userService.delete(userId);
	}
	@GetMapping("/users/allbyids")
	public List<UserDto> findAllByids(@RequestParam List<String> ids) {
		return userService.findAllByIds(ids);
	}
	
}
