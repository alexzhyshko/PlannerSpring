package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Card;
import com.zhyshko.repository.CardRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CardService {

	private final CardRepository cardRepository;
	private ModelMapper mapper = new ModelMapper();
	
	@Transactional
	public com.zhyshko.dto.Card addCard(com.zhyshko.dto.Card card) {
		return mapper.map(cardRepository.save(mapper.map(card, com.zhyshko.model.Card.class)), com.zhyshko.dto.Card.class);
	}
	
	@Transactional
	public List<com.zhyshko.dto.Card> getAllCards(){
		List<com.zhyshko.dto.Card> result = new ArrayList<>();
		for(Card card : cardRepository.findAll()) {
			result.add(mapper.map(card, com.zhyshko.dto.Card.class));
		}
		return result;
	}
	
	@Transactional
	public com.zhyshko.dto.Card getCardById(UUID id){
		return mapper.map(cardRepository.findById(id).orElse(null), com.zhyshko.dto.Card.class);
	}
	
	@Transactional
	public void deleteCard(UUID id){
		cardRepository.deleteById(id);
	}
	
	@Transactional
	public com.zhyshko.dto.Card updateCard(com.zhyshko.dto.Card card){
		return addCard(card);
	}
	
	
}
