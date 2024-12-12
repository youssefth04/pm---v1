package com.ysf.pm.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ysf.pm.models.NotificationEntity;

@FeignClient("notification-service")
public interface NotificationRestClient {
	
	@PostMapping("/api/notifications")
	public NotificationEntity addNewNotification(@RequestBody NotificationEntity notificationEntity);

	
}
