package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Notification;
import com.zhyshko.repository.CardRepository;
import com.zhyshko.repository.NotificationRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class NotificationService {

	private final NotificationRepository notificationRepository;
	private ModelMapper mapper = new ModelMapper();
	
	@Transactional
	public com.zhyshko.dto.Notification addNotification(com.zhyshko.dto.Notification notification) {
		return mapper.map(notificationRepository.save(mapper.map(notification, Notification.class)), com.zhyshko.dto.Notification.class);
		
	}
	
	
	@Transactional
	public List<com.zhyshko.dto.Notification> getAllNotifications(){
		List<com.zhyshko.dto.Notification> result = new ArrayList<>();
		for(Notification notification : notificationRepository.findAll()) {
			result.add(mapper.map(notification, com.zhyshko.dto.Notification.class));
		}
		return result;
	}
	
	
	@Transactional
	public com.zhyshko.dto.Notification getNotificationById(UUID id){
		return mapper.map(notificationRepository.findById(id).orElse(null), com.zhyshko.dto.Notification.class);
	}
	
	
	@Transactional
	public void deleteNotification(UUID id){
		notificationRepository.deleteById(id);
	}
	
	
	@Transactional
	public com.zhyshko.dto.Notification updateNotification(com.zhyshko.dto.Notification notification){
		return addNotification(notification);
	}
	
	
}
