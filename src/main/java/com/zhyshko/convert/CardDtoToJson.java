package com.zhyshko.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CardDtoToJson {
	public static com.zhyshko.json.Card toJson(com.zhyshko.dto.Card dto){
		return com.zhyshko.json.Card.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.description(dto.getDescription())
				.bottomTitle(dto.getBottomTitle())
				.date(dto.getDate())
				.time(dto.getTime())
				.workers(UserToUserBackConverter.toJson(dto.getWorkers()))
				.build();
	}

	public static List<com.zhyshko.json.Card> toJson(List<com.zhyshko.dto.Card> cards) {
		return cards.stream().map(CardDtoToJson::toJson).collect(Collectors.toList());
	}
}
