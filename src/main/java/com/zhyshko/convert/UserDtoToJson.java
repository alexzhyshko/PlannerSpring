package com.zhyshko.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserDtoToJson {

	public static com.zhyshko.json.User toJson(com.zhyshko.dto.User dto){
		return com.zhyshko.json.User.builder()
				.username(dto.getUsername())
				.id(dto.getId())
				.password(dto.getPassword())
				.email(dto.getPassword())
				.enabled(dto.isEnabled())
				.dashboards(DashboardDtoToJson.toJson(dto.getDashboards()))
				.cards(CardDtoToJson.toJson(dto.getCards()))
				.notifications(NotificationDtoToJson.toJson(dto.getNotifications()))
				.build();
	}
	
	public static List<com.zhyshko.json.User> toJson(List<com.zhyshko.dto.User> users){
		return users.stream().map(UserDtoToJson::toJson).collect(Collectors.toList());
	}
	
}
