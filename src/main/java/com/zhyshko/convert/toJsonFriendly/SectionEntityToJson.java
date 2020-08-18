package com.zhyshko.convert.toJsonFriendly;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class SectionEntityToJson {

	public static com.zhyshko.json.Section toJson(com.zhyshko.model.Section dto){
		return com.zhyshko.json.Section.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.cards(CardEntityToJson.toJson(dto.getCards()))
				.build();
	}
	
	public static List<com.zhyshko.json.Section> toJson(List<com.zhyshko.model.Section> sections){
		return sections.stream().map(SectionEntityToJson::toJson).collect(Collectors.toList());
	}
	
	
}
