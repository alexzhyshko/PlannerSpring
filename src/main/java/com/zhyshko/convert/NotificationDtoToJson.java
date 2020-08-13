package com.zhyshko.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class NotificationDtoToJson {
	public static com.zhyshko.json.Notification toJson(com.zhyshko.dto.Notification dto){
		return com.zhyshko.json.Notification.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.text(dto.getText())
				.isSeen(dto.isSeen())
				.owner(UserToUserBackConverter.toJson(dto.getOwner()))
				.build();
	}
	
	public static List<com.zhyshko.json.Notification> toJson(List<com.zhyshko.dto.Notification> notifications){
		return notifications.stream().map(NotificationDtoToJson::toJson).collect(Collectors.toList());
	}
}
