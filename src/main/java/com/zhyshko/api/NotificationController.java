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

import com.zhyshko.convert.toJsonFriendly.NotificationEntityToJson;
import com.zhyshko.model.Notification;
import com.zhyshko.model.User;
import com.zhyshko.service.NotificationService;
import com.zhyshko.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

	private final NotificationService notificationService;
	private final UserService userService;
	
	@GetMapping
	public List<com.zhyshko.json.Notification> getAllCards() {
		return NotificationEntityToJson.toJson(notificationService.getAllNotifications());
	}
	
	@PostMapping("/userAddNotifications")
	public ResponseEntity<String> addNotificationToUser(@RequestBody Map<String, String> json){
		UUID notificationid = UUID.fromString(json.get("notificationid"));
		UUID userid = UUID.fromString(json.get("userid"));
		Notification notification = notificationService.getNotificationById(notificationid);
		User user = userService.getUserById(userid);
		notification.setOwner(user);
		user.getNotifications().add(notification);
		userService.updateUser(user);
		notificationService.updateNotification(notification);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
	@PostMapping("/userRemoveNotification")
	public ResponseEntity<String> removeNotificcationFromUser(@RequestBody Map<String, String> json){
		UUID notificationid = UUID.fromString(json.get("notificationid"));
		UUID userid = UUID.fromString(json.get("userid"));
		Notification notification = notificationService.getNotificationById(notificationid);
		User user = userService.getUserById(userid);
		notification.setOwner(null);
		user.getNotifications().remove(notification);
		userService.updateUser(user);
		notificationService.updateNotification(notification);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
	
	
}
