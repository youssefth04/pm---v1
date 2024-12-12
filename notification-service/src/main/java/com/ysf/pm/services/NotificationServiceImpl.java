package com.ysf.pm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ysf.pm.dtos.NotificationDto;
import com.ysf.pm.exceptions.NotificationNotFoundException;
import com.ysf.pm.feign.UserRestClient;
import com.ysf.pm.mappers.NotificationMapper;
import com.ysf.pm.models.UserEntity;
import com.ysf.pm.repositories.NotificationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;
	private final NotificationMapper notificationMapper;
	private final UserRestClient userRestClient;

	@Override
	public NotificationDto save(NotificationDto notificationDto) {
		return notificationMapper.toDto(notificationRepository.save(notificationMapper.toEntity(notificationDto)));
	}

	@Override
	public void deleteById(Long notificationId) throws NotificationNotFoundException {
		NotificationDto notification = findById(notificationId);
		if (notification != null) {
			notificationRepository.deleteById(notificationId);

		} else
			throw new NotificationNotFoundException("notifcation not found ");

	}

	@Override
	public List<NotificationDto> findAllByUserId(String userId) {
		UserEntity userEntity = userRestClient.findBydUserId(userId);
		List<NotificationDto> listOfNotif = notificationRepository.finallbyuserId(userId).stream()
				.map(notificationMapper::toDto).collect(Collectors.toList());
		listOfNotif.forEach(n -> {
			n.setUserEntity(userEntity);
		});
		return listOfNotif;
	}

	@Override
	public NotificationDto update(NotificationDto notificationDto) throws NotificationNotFoundException {
		NotificationDto notification = findById(notificationDto.getId());
		if (notification != null) {
			return notificationMapper.toDto(notificationRepository.save(notificationMapper.toEntity(notificationDto)));
		} else
			throw new NotificationNotFoundException("notification not found");
	}

	@Override
	public NotificationDto findById(Long notificationId) throws NotificationNotFoundException {
		
		NotificationDto notificationDto= notificationMapper.toDto( notificationRepository.findById(notificationId).orElseThrow(()-> 
		new NotificationNotFoundException("notification not found")));
		notificationDto.setUserEntity( userRestClient.findBydUserId(notificationDto.getUserId()));
		return notificationDto;
		
	}

}
