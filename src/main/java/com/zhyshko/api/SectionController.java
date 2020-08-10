package com.zhyshko.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhyshko.model.Card;
import com.zhyshko.model.Section;
import com.zhyshko.service.CardService;
import com.zhyshko.service.SectionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/section")
public class SectionController {

	private final SectionService sectionService;
	private final CardService cardService;
	
	
	@GetMapping
	public List<Section> getAllSections() {
		return sectionService.getAllSections();
	}
	
	@GetMapping("/by-user/{username}/{dashboardid}/{sectionid}")
	public ResponseEntity<Object> getSectionByUser(@PathVariable("username") String username, @PathVariable("dashboardid") UUID dashboardid, @PathVariable("sectionid") UUID sectionid) {
		Section result = sectionService.getSectionByUser(username, dashboardid, sectionid);
		if(result==null) {
			return new ResponseEntity<>("No such section", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@PostMapping("/createCard/{sectionid}")
	public ResponseEntity<String> addSection(@PathVariable("sectionid") UUID sectionid, @RequestBody Card card) {
		Section section = sectionService.getSectionById(sectionid);
		section.getCards().add(card);
		card.setSection(section);
		sectionService.updateSection(section);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/removeCard")
	public ResponseEntity<String> removeSection(@RequestBody Map<String, String> json) {
		UUID sectionid = UUID.fromString(json.get("sectionid"));
		UUID cardid =  UUID.fromString(json.get("cardid"));
		Section section = sectionService.getSectionById(sectionid);
		Card card = cardService.getCardById(cardid);
		section.getCards().remove(card);
		card.setSection(null);
		sectionService.updateSection(section);
		cardService.updateCard(card);
		cardService.deleteCard(cardid);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
}
