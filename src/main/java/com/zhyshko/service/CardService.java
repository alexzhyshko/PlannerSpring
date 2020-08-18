package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Card;
import com.zhyshko.repository.CardRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CardService {

	private final CardRepository cardRepository;
	
	@Transactional
	public com.zhyshko.model.Card addCard(com.zhyshko.model.Card card) {
		return cardRepository.save(card);
	}
	
	@Transactional
	public List<com.zhyshko.model.Card> getAllCards(){
		List<com.zhyshko.model.Card> result = new ArrayList<>();
		for(Card card : cardRepository.findAll()) {
			result.add(card);
		}
		return result;
	}
	
	@Transactional
	public com.zhyshko.model.Card getCardById(UUID id){
		return cardRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public void deleteCard(UUID id){
		cardRepository.deleteById(id);
	}
	
	@Transactional
	public com.zhyshko.model.Card updateCard(com.zhyshko.model.Card card){
		return addCard(card);
	}
	
	
}
