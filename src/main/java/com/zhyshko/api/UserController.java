package com.zhyshko.api;

import java.util.Collections;
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

import com.zhyshko.model.Dashboard;
import com.zhyshko.model.User;
import com.zhyshko.service.DashboardService;
import com.zhyshko.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;
	private final DashboardService dashboardService;
	
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{username}")
	public User getUserById(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
	
	
	
	@PostMapping("/createDashboard/{userid}")
	public ResponseEntity<String> createDashboard(@PathVariable("userid") UUID userid, @RequestBody Dashboard dashboard) {
		User user = userService.getUserById(userid);
		dashboard.setCreator(user);
		user.getDashboards().add(dashboard);
		userService.updateUser(user);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
	@PostMapping("/joinDashboard")
	public ResponseEntity<String> joinDashboard(@RequestBody Map<String, String> json) {
		UUID userid = UUID.fromString(json.get("userid"));
		UUID dashboardid =  UUID.fromString(json.get("dashboardid"));
		User user = userService.getUserById(userid);
		Dashboard dashboard = dashboardService.getDashboardById(dashboardid);
		user.getDashboards().add(dashboard);
		userService.updateUser(user);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
	
	@PostMapping("/leaveDashboard")
	public ResponseEntity<String> leaveDashboard(@RequestBody Map<String, String> json) {
		UUID userid = UUID.fromString(json.get("userid"));
		UUID dashboardid =  UUID.fromString(json.get("dashboardid"));
		User user = userService.getUserById(userid);
		Dashboard dashboard = dashboardService.getDashboardById(dashboardid);
		user.getDashboards().remove(dashboard);
		userService.updateUser(user);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
	@GetMapping("/deleteDashboard/{dashboardid}")
	public ResponseEntity<String> leaveDashboard(@PathVariable UUID dashboardid) {
		Dashboard dashboard = dashboardService.getDashboardById(dashboardid);
		for(User user : dashboard.getUsers()) {
			user.getDashboards().remove(dashboard);
			userService.updateUser(user);
		}
		dashboardService.deleteDashboard(dashboardid);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
}
