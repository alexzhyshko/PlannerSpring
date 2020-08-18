package com.zhyshko.convert.toJsonFriendly;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CardEntityToJson {
	public static com.zhyshko.json.Card toJson(com.zhyshko.model.Card dto){
		return com.zhyshko.json.Card.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.description(dto.getDescription())
				.bottomTitle(dto.getBottomTitle())
				.date(dto.getDate())
				.time(dto.getTime())
				.workers(UserToUserBackConverter.toJson(dto.getWorkers()))
				.section(SectionToSectionBackConverter.toJson(dto.getSection()))
				.build();
	}

	public static List<com.zhyshko.json.Card> toJson(List<com.zhyshko.model.Card> cards) {
		return cards.stream().map(CardEntityToJson::toJson).collect(Collectors.toList());
	}
}
