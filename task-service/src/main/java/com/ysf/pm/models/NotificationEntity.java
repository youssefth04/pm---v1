package com.ysf.pm.models;

import lombok.Data;

@Data
public class NotificationEntity {
	
	
	private Long id;
	private String message;

	private UserEntity userEntity;
	
	private String userId;

}
