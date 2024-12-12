package com.ysf.pm.entities;

import com.ysf.pm.models.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
@Entity
@Data
public class NotificationEntity {
	
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private String message;
	@Transient
	private UserEntity userEntity;
	
	private String userId;
	
}
