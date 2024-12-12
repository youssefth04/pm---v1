package com.ysf.pm.services;

import java.util.List;

import com.ysf.pm.dtos.UserDto;
import com.ysf.pm.exception.UserNotFoundException;

public interface UserService {
	
	public UserDto findByid(String userId) throws UserNotFoundException;
	
	public void delete(String userId) throws UserNotFoundException; 

	public List<UserDto> findAll();
	
	public UserDto save(UserDto userDto);
	
	public UserDto update(UserDto userDto) throws UserNotFoundException;

	List<UserDto> findAllByIds(Iterable<String> ids);
	
	




}

