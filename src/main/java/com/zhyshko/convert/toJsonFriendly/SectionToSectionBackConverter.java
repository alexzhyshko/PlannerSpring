package com.zhyshko.convert.toJsonFriendly;

import java.util.List;
import java.util.stream.Collectors;

public class SectionToSectionBackConverter {

	public static com.zhyshko.json.Section toJson(com.zhyshko.model.Section dto){
		return com.zhyshko.json.Section.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.build();
	}

	public static List<com.zhyshko.json.Section> toJson(List<com.zhyshko.model.Section> cards) {
		return cards.stream().map(SectionToSectionBackConverter::toJson).collect(Collectors.toList());
	}
	
}
