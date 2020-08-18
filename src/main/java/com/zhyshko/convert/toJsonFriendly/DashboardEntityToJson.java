package com.zhyshko.convert.toJsonFriendly;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


@Component
public class DashboardEntityToJson {

	public static com.zhyshko.json.Dashboard toJson(com.zhyshko.model.Dashboard dto){
		return com.zhyshko.json.Dashboard.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.creatorId(dto.getCreatorId())
				.users(UserToUserBackConverter.toJson(dto.getUsers()))
				.sections(SectionEntityToJson.toJson(dto.getSections()))
				.build();
	}
	
	public static List<com.zhyshko.json.Dashboard> toJson(List<com.zhyshko.model.Dashboard> dashboards){
		return dashboards.stream().map(DashboardEntityToJson::toJson).collect(Collectors.toList());
	}
	
}
