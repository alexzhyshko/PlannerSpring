package com.zhyshko.convert.toJsonFriendly;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardToDashboardBackConverter {

	public static com.zhyshko.json.DashboardBack toJson(com.zhyshko.model.Dashboard dto){
		return com.zhyshko.json.DashboardBack.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.creatorId(dto.getCreatorId())
				.build();
	}

	public static List<com.zhyshko.json.DashboardBack> toJson(List<com.zhyshko.model.Dashboard> cards) {
		return cards.stream().map(DashboardToDashboardBackConverter::toJson).collect(Collectors.toList());
	}
	
}
