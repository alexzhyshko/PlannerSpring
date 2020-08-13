package com.zhyshko.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


@Component
public class DashboardDtoToJson {

	public static com.zhyshko.json.Dashboard toJson(com.zhyshko.dto.Dashboard dto){
		return com.zhyshko.json.Dashboard.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.creatorId(dto.getCreatorId())
				.users(UserToUserBackConverter.toJson(dto.getUsers()))
				.sections(SectionDtoToJson.toJson(dto.getSections()))
				.build();
	}
	
	public static List<com.zhyshko.json.Dashboard> toJson(List<com.zhyshko.dto.Dashboard> dashboards){
		return dashboards.stream().map(DashboardDtoToJson::toJson).collect(Collectors.toList());
	}
	
}
