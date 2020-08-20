package com.zhyshko.convert.toJsonFriendly;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserEntityToJson {

	public static com.zhyshko.json.User toJson(com.zhyshko.model.User dto){
		
		return com.zhyshko.json.User.builder()
				.username(dto.getUsername())
				.id(dto.getId())
				.password(dto.getPassword())
				.email(dto.getEmail())
				.enabled(dto.isEnabled())
				.dashboards(DashboardEntityToJson.toJson(dto.getDashboards()))
				.cards(CardEntityToJson.toJson(dto.getCards()))
				.notifications(NotificationEntityToJson.toJson(dto.getNotifications()))
				.build();
	}
	
	public static List<com.zhyshko.json.User> toJson(List<com.zhyshko.model.User> users){
		return users.stream().map(UserEntityToJson::toJson).collect(Collectors.toList());
	}
	
}
