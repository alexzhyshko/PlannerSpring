package com.zhyshko.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhyshko.convert.toJsonFriendly.CardEntityToJson;
import com.zhyshko.factory.NotificationFactory;
import com.zhyshko.model.Card;
import com.zhyshko.model.User;
import com.zhyshko.service.CardService;
import com.zhyshko.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/card")
public class CardController {

	private final CardService cardService;
	private final UserService userService;
	private final NotificationFactory notificationFactory;

	@GetMapping
	public List<com.zhyshko.json.Card> getAllCards() {
		return CardEntityToJson.toJson(cardService.getAllCards());
	}

	@PostMapping("/userJoinCard")
	public ResponseEntity<String> addUserToCard(@RequestBody Map<String, String> json) {
		UUID cardid = UUID.fromString(json.get("cardid"));
		UUID userid = UUID.fromString(json.get("userid"));
		Card card = cardService.getCardById(cardid);
		User user = userService.getUserById(userid);
		if (card.getWorkers().contains(user)) {
			return new ResponseEntity<>("Conflict", HttpStatus.CONFLICT);
		}
		if (user.getCards().contains(card)) {
			return new ResponseEntity<>("Conflict", HttpStatus.CONFLICT);
		}
		for (User temp : card.getWorkers()) {
			user.getNotifications().add(this.notificationFactory.fillUserJoinedCardTemplate(card, temp, user));
			userService.updateUser(temp);
		}
		card.getWorkers().add(user);
		user.getCards().add(card);
		userService.updateUser(user);
		cardService.updateCard(card);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}

	@PostMapping("/userLeaveCard")
	public ResponseEntity<String> leaveUserFromCard(@RequestBody Map<String, String> json) {
		UUID cardid = UUID.fromString(json.get("cardid"));
		UUID userid = UUID.fromString(json.get("userid"));
		Card card = cardService.getCardById(cardid);
		User user = userService.getUserById(userid);
		card.getWorkers().remove(user);
		user.getCards().remove(card);
		for (User temp : card.getWorkers()) {
			user.getNotifications().add(this.notificationFactory.fillUserLeftCardTemplate(card, temp, user));
			userService.updateUser(temp);
		}
		userService.updateUser(user);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}

}