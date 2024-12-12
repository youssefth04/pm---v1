package com.ysf.pm.services;

import java.util.List;

import com.ysf.pm.dtos.NotificationDto;
import com.ysf.pm.exceptions.NotificationNotFoundException;

public interface NotificationService {

	NotificationDto save(NotificationDto notificationDto);

	void deleteById(Long notificationId) throws NotificationNotFoundException ;
	List<NotificationDto> findAllByUserId(String userId);
	
	NotificationDto update (NotificationDto notificationDto) throws NotificationNotFoundException;
	
	NotificationDto findById(Long notificationId) throws NotificationNotFoundException;
	
	


}
