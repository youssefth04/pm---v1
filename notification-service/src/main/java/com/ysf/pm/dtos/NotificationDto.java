package com.ysf.pm.dtos;

import com.ysf.pm.models.UserEntity;

import lombok.Data;

@Data
public class NotificationDto {
	
	private Long id;
	private String message;

	private UserEntity userEntity;
	
	private String userId;
	

}
