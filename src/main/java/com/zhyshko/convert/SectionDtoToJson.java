package com.zhyshko.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class SectionDtoToJson {

	public static com.zhyshko.json.Section toJson(com.zhyshko.dto.Section dto){
		return com.zhyshko.json.Section.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.cards(CardDtoToJson.toJson(dto.getCards()))
				.build();
	}
	
	public static List<com.zhyshko.json.Section> toJson(List<com.zhyshko.dto.Section> sections){
		return sections.stream().map(SectionDtoToJson::toJson).collect(Collectors.toList());
	}
	
	
}
