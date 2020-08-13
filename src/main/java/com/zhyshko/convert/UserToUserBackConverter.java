package com.zhyshko.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserToUserBackConverter {

	public static com.zhyshko.json.UserBack toJson(com.zhyshko.dto.User dto){
		return com.zhyshko.json.UserBack.builder()
				.username(dto.getUsername())
				.id(dto.getId())
				.password(dto.getPassword())
				.email(dto.getPassword())
				.enabled(dto.isEnabled())
				.build();
	}
	
	public static List<com.zhyshko.json.UserBack> toJson(List<com.zhyshko.dto.User> users){
		return users.stream().map(UserToUserBackConverter::toJson).collect(Collectors.toList());
	}
	
}
