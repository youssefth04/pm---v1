package com.ysf.pm.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ysf.pm.dtos.UserDto;
import com.ysf.pm.entities.UserEntity;

@Component
public class UserMapper {

	public UserDto toDto(UserEntity userentity) {
		UserDto userDto= new UserDto();
		BeanUtils.copyProperties(userentity, userDto);
		return userDto;
	}
	
	public UserEntity toEntity(UserDto userDto) {
		UserEntity userEntity= new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		return userEntity;
	}
}
