package com.ysf.pm.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysf.pm.dtos.NotificationDto;
import com.ysf.pm.exceptions.NotificationNotFoundException;
import com.ysf.pm.services.NotificationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/notifications")
@RestController
public class NotificationRestController {
	
	
	private final NotificationService notificationService;
	
	@PostMapping
	public  NotificationDto save(@RequestBody NotificationDto notificationDto) {
		return notificationService.save(notificationDto);
	}
	@DeleteMapping("/{notificationId}")
	public void deleteById(@PathVariable Long notificationId) throws NotificationNotFoundException {
		notificationService.deleteById(notificationId);
	}
	@GetMapping("/byuser/{userId}")
	public  List<NotificationDto> findAllByUserId(@PathVariable String userId){
		return notificationService.findAllByUserId(userId);
	}
	
	@PutMapping
	public NotificationDto update (@RequestBody NotificationDto notificationDto) throws NotificationNotFoundException{
		return notificationService.update(notificationDto);
	}
	
	@GetMapping("/{notificationId}")
	public NotificationDto findById(@PathVariable Long notificationId) throws NotificationNotFoundException{
		return notificationService.findById(notificationId);
	}

}
