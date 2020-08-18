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

import com.zhyshko.convert.toJsonFriendly.UserEntityToJson;
import com.zhyshko.factory.NotificationFactory;
import com.zhyshko.model.Dashboard;
import com.zhyshko.model.Notification;
import com.zhyshko.model.Section;
import com.zhyshko.model.User;
import com.zhyshko.service.DashboardService;
import com.zhyshko.service.NotificationService;
import com.zhyshko.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;
	private final DashboardService dashboardService;
	private final NotificationFactory notificationFactory;
	private final NotificationService notificationService;

	@GetMapping
	public List<com.zhyshko.json.User> getAllUsers() {
		return UserEntityToJson.toJson(userService.getAllUsers());
	}

	@GetMapping("/{username}")
	public com.zhyshko.json.User getUserByUsername(@PathVariable("username") String username) {
		return UserEntityToJson.toJson(userService.getUserByUsername(username));
	}

	@PostMapping("/createDashboard/{userid}")
	public ResponseEntity<String> createDashboard(@PathVariable("userid") String id, @RequestBody Dashboard dashboard) {
		User user = null;
		try {
			user = userService.getUserById(UUID.fromString(id));
		} catch (Exception e) {
			user = userService.getUserByUsername(id);
		}
		dashboard.setCreatorId(user.getId());
		user.getDashboards().add(dashboard);
		userService.updateUser(user);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}

	@PostMapping("/joinDashboard")
	public ResponseEntity<String> joinDashboard(@RequestBody Map<String, String> json) {
		User user = null;
		try {
			UUID userid = UUID.fromString(json.get("userid"));
			user = userService.getUserById(userid);
		} catch (Exception e) {
			String username = json.get("username");
			user = userService.getUserByUsername(username);
		}
		UUID dashboardid = UUID.fromString(json.get("dashboardid"));
		Dashboard dashboard = dashboardService.getDashboardById(dashboardid);
		if (dashboard == null) {
			return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
		}
		if (user.getDashboards().contains(dashboard)) {
			return new ResponseEntity<>("Already joined", HttpStatus.CONFLICT);
		}
		user.getDashboards().add(dashboard);
		userService.updateUser(user);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}

	@GetMapping("/leaveDashboard/{dashboardid}/{userid}")
	public ResponseEntity<String> leaveDashboard(@PathVariable("") UUID dashboardid, @PathVariable UUID userid) {
		User user = userService.getUserById(userid);
		Dashboard dashboard = dashboardService.getDashboardById(dashboardid);
		user.getDashboards().remove(dashboard);
		userService.updateUser(user);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}

	@GetMapping("/deleteDashboard/{dashboardid}")
	public ResponseEntity<String> leaveDashboard(@PathVariable UUID dashboardid) {
		Dashboard dashboard = dashboardService.getDashboardById(dashboardid);
		for (User user : dashboard.getUsers()) {
			user.getDashboards().remove(dashboard);
			user.getNotifications().add(this.notificationFactory.fillDashboardDeletedTemplate(dashboard, user));
			for (Section section : dashboard.getSections()) {
				user.getCards().removeAll(section.getCards());
			}
			userService.updateUser(user);
		}
		dashboardService.deleteDashboard(dashboardid);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}

	@GetMapping("/readAllNotifications/{userid}")
	public ResponseEntity<String> readAllNotifications(@PathVariable UUID userid) {
		User user = userService.getUserById(userid);
		for (Notification notification : user.getNotifications()) {
			notification.setSeen(true);
		}
		userService.updateUser(user);
		return new ResponseEntity<>("Done", HttpStatus.OK);
	}

	@GetMapping("/clearAllNotifications/{userid}")
	public ResponseEntity<String> clearAllNotifications(@PathVariable UUID userid) {
		User user = userService.getUserById(userid);
		List<Notification> temp = user.getNotifications();
		user.getNotifications().clear();
		userService.updateUser(user);
		for (Notification notification : temp) {
			notification.setOwner(null);
			notificationService.deleteNotification(notification.getId());
		}
		return new ResponseEntity<>("Done", HttpStatus.OK);
	}

}
