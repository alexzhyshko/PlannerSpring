package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Card;
import com.zhyshko.repository.CardRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class CardService {

	private final CardRepository cardRepository;
	
	@Transactional
	public Card addCard(Card card) {
		return cardRepository.save(card);
	}
	
	@Transactional
	public List<Card> getAllCards(){
		List<Card> result = new ArrayList<>();
		for(Card card : cardRepository.findAll()) {
			result.add(card);
		}
		return result;
	}
	
	@Transactional
	public Card getCardById(UUID id){
		return cardRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public void deleteCard(UUID id){
		cardRepository.deleteById(id);
	}
	
	@Transactional
	public Card updateCard(Card card){
		return cardRepository.save(card);
	}
	
	
}
