package com.zhyshko.convert.entity2dto;

import java.util.ArrayList;
import java.util.List;

import com.zhyshko.convert.entity2dto.context.ContextManager;
import com.zhyshko.convert.entity2dto.context.CycleAvoidingMappingContextCardToDto;
import com.zhyshko.dto.Card;
import com.zhyshko.dto.Card.CardBuilder;

public class CardEntityToDTO {

	public static Card cardToCard(com.zhyshko.model.Card card) {
		if (card == null) {
			return null;
		}

		CycleAvoidingMappingContextCardToDto context = ContextManager.getCardContext();

		com.zhyshko.dto.Card contextCard = context.getMappedInstance(card, com.zhyshko.dto.Card.class);
		
		if (contextCard == null) {
			CardBuilder card1 = Card.builder();
			card1.id(card.getId());
			card1.title(card.getTitle());
			card1.description(card.getDescription());
			card1.bottomTitle(card.getBottomTitle());
			card1.date(card.getDate());
			card1.time(card.getTime());
			com.zhyshko.dto.Card result = card1.build();
			context.storeMappedInstance(card, result);
			return result;
		}

//		if(contextCard.getWorkers() == null) {
//			contextCard.setWorkers(UserEntityToDTO.userListToUserList(card.getWorkers()));
//		}
//		
//		if(contextCard.getSection() == null) {
//			contextCard.setSection(SectionEntityToDTO.sectionToSection(card.getSection()));
//		}
		
		context.storeMappedInstance(card, contextCard);
		return contextCard;

		
	}

	public static List<Card> cardListToCardList(List<com.zhyshko.model.Card> list) {
		if (list == null) {
			return null;
		}

		List<Card> list1 = new ArrayList<Card>(list.size());
		for (com.zhyshko.model.Card card : list) {
			list1.add(cardToCard(card));
		}

		return list1;
	}

}
