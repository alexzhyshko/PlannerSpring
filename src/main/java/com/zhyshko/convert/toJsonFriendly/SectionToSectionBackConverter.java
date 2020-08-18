package com.zhyshko.convert.toJsonFriendly;

import java.util.List;
import java.util.stream.Collectors;

public class SectionToSectionBackConverter {

	public static com.zhyshko.json.SectionBack toJson(com.zhyshko.model.Section dto){
		return com.zhyshko.json.SectionBack.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.dashboard(DashboardToDashboardBackConverter.toJson(dto.getDashboard()))
				.build();
	}

	public static List<com.zhyshko.json.SectionBack> toJson(List<com.zhyshko.model.Section> cards) {
		return cards.stream().map(SectionToSectionBackConverter::toJson).collect(Collectors.toList());
	}
	
}
