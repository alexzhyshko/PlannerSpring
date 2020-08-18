package com.zhyshko.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhyshko.convert.toJsonFriendly.SectionEntityToJson;
import com.zhyshko.factory.NotificationFactory;
import com.zhyshko.model.Card;
import com.zhyshko.model.Section;
import com.zhyshko.model.User;
import com.zhyshko.service.CardService;
import com.zhyshko.service.SectionService;
import com.zhyshko.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/section")
public class SectionController {

	private final UserService userService;
	private final SectionService sectionService;
	private final CardService cardService;
	private final NotificationFactory notificationFactory;
	
	
	
	@GetMapping
	public List<com.zhyshko.json.Section> getAllSections() {
		return SectionEntityToJson.toJson(sectionService.getAllSections());
	}
	
	@GetMapping("/by-user/{username}/{dashboardid}/{sectionid}")
	public ResponseEntity<Object> getSectionByUser(@PathVariable("username") String username, @PathVariable("dashboardid") UUID dashboardid, @PathVariable("sectionid") UUID sectionid) {
		com.zhyshko.json.Section result =  SectionEntityToJson.toJson(sectionService.getSectionByUser(username, dashboardid, sectionid));
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
		for (User user : section.getDashboard().getUsers()) {
			user.getNotifications().add(this.notificationFactory.fillCardAddedTemplate(card, user));
			userService.updateUser(user);
		}
		sectionService.updateSection(section);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
	@PostMapping("/removeCard")
	public ResponseEntity<String> removeSection(@RequestBody Map<String, String> json) {
		UUID sectionid = UUID.fromString(json.get("sectionid"));
		UUID cardid =  UUID.fromString(json.get("cardid"));
		Section section = sectionService.getSectionById(sectionid);
		Card card = cardService.getCardById(cardid);
		for (User user : card.getWorkers()) {
			user.getCards().remove(card);
			user.getNotifications().add(this.notificationFactory.fillCardDeletedTemplate(card, user));
			userService.updateUser(user);
		}
		section.getCards().remove(card);
		card.setSection(null);
		sectionService.updateSection(section);
		cardService.updateCard(card);
		cardService.deleteCard(cardid);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
}
