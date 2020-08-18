package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Notification;
import com.zhyshko.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class NotificationService {

	private final NotificationRepository notificationRepository;
	
	@Transactional
	public com.zhyshko.model.Notification addNotification(com.zhyshko.model.Notification notification) {
		return notificationRepository.save(notification);
		
	}
	
	
	@Transactional
	public List<com.zhyshko.model.Notification> getAllNotifications(){
		List<com.zhyshko.model.Notification> result = new ArrayList<>();
		for(Notification notification : notificationRepository.findAll()) {
			result.add(notification);
		}
		return result;
	}
	
	
	@Transactional
	public com.zhyshko.model.Notification getNotificationById(UUID id){
		return notificationRepository.findById(id).orElse(null);
	}
	
	
	@Transactional
	public void deleteNotification(UUID id){
		notificationRepository.deleteById(id);
	}
	
	
	@Transactional
	public com.zhyshko.model.Notification updateNotification(com.zhyshko.model.Notification notification){
		return addNotification(notification);
	}
	
	
}
