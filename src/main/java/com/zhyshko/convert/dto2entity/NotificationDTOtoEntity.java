package com.zhyshko.convert.dto2entity;

import java.util.ArrayList;
import java.util.List;

import com.zhyshko.convert.dto2entity.context.ContextManager;
import com.zhyshko.convert.dto2entity.context.CycleAvoidingMappingContextNotificationToEntity;
import com.zhyshko.model.Notification;
import com.zhyshko.model.Notification.NotificationBuilder;

public class NotificationDTOtoEntity {

	public static Notification notificationToNotification(com.zhyshko.dto.Notification notification) {
		if (notification == null) {
			return null;
		}

		CycleAvoidingMappingContextNotificationToEntity context = ContextManager.getNotificationContext();

		if (context.getMappedInstance(notification, com.zhyshko.model.Notification.class) != null) {
			return context.getMappedInstance(notification, com.zhyshko.model.Notification.class);
		}
		
		NotificationBuilder notification1 = Notification.builder();

		notification1.id(notification.getId());
		notification1.title(notification.getTitle());
		notification1.text(notification.getText());
		notification1.owner(UserDTOtoEntity.userDtoToUser(notification.getOwner()));

		com.zhyshko.model.Notification result = notification1.build();

		context.storeMappedInstance(notification, result);

		return result;
	}

	public static List<Notification> notificationListToNotificationList(List<com.zhyshko.dto.Notification> list) {
		if (list == null) {
			return null;
		}

		List<Notification> list1 = new ArrayList<Notification>(list.size());
		for (com.zhyshko.dto.Notification notification : list) {
			list1.add(notificationToNotification(notification));
		}

		return list1;
	}
}
