package com.zhyshko.convert.toJsonFriendly;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class NotificationEntityToJson {
	public static com.zhyshko.json.Notification toJson(com.zhyshko.model.Notification dto){
		return com.zhyshko.json.Notification.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.text(dto.getText())
				.isSeen(dto.isSeen())
				.owner(UserToUserBackConverter.toJson(dto.getOwner()))
				.created(dto.getCreated())
				.build();
	}
	
	public static List<com.zhyshko.json.Notification> toJson(List<com.zhyshko.model.Notification> notifications){
		return notifications.stream().map(NotificationEntityToJson::toJson).collect(Collectors.toList());
	}
}
