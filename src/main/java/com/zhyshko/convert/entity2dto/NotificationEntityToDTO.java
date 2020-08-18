package com.zhyshko.convert.entity2dto;

import java.util.ArrayList;
import java.util.List;

import com.zhyshko.convert.entity2dto.context.ContextManager;
import com.zhyshko.convert.entity2dto.context.CycleAvoidingMappingContextNotificationToDto;
import com.zhyshko.dto.Notification.NotificationBuilder;
import com.zhyshko.model.Notification;

public class NotificationEntityToDTO {

	public static com.zhyshko.dto.Notification notificationToNotification(Notification notification) {
		if (notification == null) {
			return null;
		}

		CycleAvoidingMappingContextNotificationToDto context = ContextManager.getNotificationContext();

		com.zhyshko.dto.Notification contextNotification = context.getMappedInstance(notification, com.zhyshko.dto.Notification.class);
		
		if (contextNotification == null) {
			NotificationBuilder notification1 = com.zhyshko.dto.Notification.builder();
			notification1.id(notification.getId());
			notification1.title(notification.getTitle());
			notification1.text(notification.getText());
			com.zhyshko.dto.Notification result = notification1.build();
			context.storeMappedInstance(notification, result);
			return result;
		}

		if(contextNotification.getOwner() == null) {
			contextNotification.setOwner(UserEntityToDTO.userToUserDto(notification.getOwner()));
		}
		
		context.storeMappedInstance(notification, contextNotification);
		return contextNotification;

		
	}

	public static List<com.zhyshko.dto.Notification> notificationListToNotificationList(List<Notification> list) {
		if (list == null) {
			return null;
		}

		List<com.zhyshko.dto.Notification> list1 = new ArrayList<com.zhyshko.dto.Notification>(list.size());
		for (Notification notification : list) {
			list1.add(notificationToNotification(notification));
		}

		return list1;
	}

}
