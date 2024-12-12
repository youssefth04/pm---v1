package com.ysf.pm.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ysf.pm.dtos.NotificationDto;
import com.ysf.pm.entities.NotificationEntity;

@Component
public class NotificationMapper {
	
	public NotificationDto toDto(NotificationEntity notificationEntity) {
		NotificationDto notificationDto= new NotificationDto();
		BeanUtils.copyProperties(notificationEntity, notificationDto);
		return notificationDto;
	}
	
	public NotificationEntity toEntity(NotificationDto notificationDto) {
		NotificationEntity notificationEntity= new NotificationEntity();
		BeanUtils.copyProperties(notificationDto, notificationEntity);
		return notificationEntity;
	}

}
