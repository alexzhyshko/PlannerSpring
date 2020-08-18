package com.zhyshko.convert.dto2entity;

import java.util.ArrayList;
import java.util.List;

import com.zhyshko.convert.dto2entity.context.ContextManager;
import com.zhyshko.convert.dto2entity.context.CycleAvoidingMappingContextCardToEntity;
import com.zhyshko.dto.Card;
import com.zhyshko.model.Card.CardBuilder;

public class CardDTOtoEntity {

	public static com.zhyshko.model.Card cardToCard(Card card) {
		if (card == null) {
			return null;
		}

		CycleAvoidingMappingContextCardToEntity context = ContextManager.getCardContext();

		com.zhyshko.model.Card contextCard = context.getMappedInstance(card, com.zhyshko.model.Card.class);
		
		if (contextCard == null) {
			CardBuilder card1 = com.zhyshko.model.Card.builder();
			card1.id(card.getId());
			card1.title(card.getTitle());
			card1.description(card.getDescription());
			card1.bottomTitle(card.getBottomTitle());
			card1.date(card.getDate());
			card1.time(card.getTime());
			contextCard = card1.build();
			context.storeMappedInstance(card, contextCard);
		}
		
		if(contextCard.getSection() == null) {
			contextCard.setSection(SectionDTOtoEntity.sectionToSection(card.getSection()));
		}

		if(contextCard.getWorkers() == null) {
			contextCard.setWorkers(UserDTOtoEntity.userListToUserList(card.getWorkers()));
		}
		
		
		context.storeMappedInstance(card, contextCard);
		return contextCard;
		

		
	}

	public static List<com.zhyshko.model.Card> cardListToCardList(List<Card> list) {
		if (list == null) {
			return null;
		}

		List<com.zhyshko.model.Card> list1 = new ArrayList<com.zhyshko.model.Card>(list.size());
		for (Card card : list) {
			list1.add(cardToCard(card));
		}

		return list1;
	}

}
