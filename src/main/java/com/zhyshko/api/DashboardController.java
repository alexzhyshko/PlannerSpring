package com.zhyshko.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhyshko.convert.toJsonFriendly.DashboardEntityToJson;
import com.zhyshko.factory.NotificationFactory;
import com.zhyshko.model.Dashboard;
import com.zhyshko.model.Section;
import com.zhyshko.model.User;
import com.zhyshko.service.DashboardService;
import com.zhyshko.service.SectionService;
import com.zhyshko.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	private final UserService userService;
	private final DashboardService dashboardService;
	private final SectionService sectionService;
	private final NotificationFactory notificationFactory;
	
	
	@PostMapping("/create")
	public ResponseEntity<String> addUser(@RequestBody Dashboard dashboard) {
		dashboardService.addDashboard(dashboard);
		return new ResponseEntity<>("Added", HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<com.zhyshko.json.Dashboard> getAllDashboards() {
		return DashboardEntityToJson.toJson(dashboardService.getAllDashboards());
	}
		
	@GetMapping("/by-user/{username}/{dashboardid}")
	public ResponseEntity<Object> getDashboardByUser(@PathVariable("username") String username, @PathVariable("dashboardid") UUID dashboardid) {
		com.zhyshko.json.Dashboard result = DashboardEntityToJson.toJson(dashboardService.getDashboardByUser(username, dashboardid));
		if(result==null) {
			return new ResponseEntity<>("No such dashboard", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/createSection/{dashboardid}")
	public ResponseEntity<String> addSection(@PathVariable("dashboardid") UUID dashboardid, @RequestBody Section section) {
		Dashboard dashboard = dashboardService.getDashboardById(dashboardid);
		dashboard.getSections().add(section);
		section.setDashboard(dashboard);
		for(User user : dashboard.getUsers()) {
			user.getNotifications().add(this.notificationFactory.fillSectionAddedTemplate(section, user));
			userService.updateUser(user);
		}
		dashboardService.updateDashboard(dashboard);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
	@GetMapping("/removeSection/{sectionid}")
	public ResponseEntity<String> removeSection(@PathVariable("sectionid") UUID sectionid) {
		Section section = sectionService.getSectionById(sectionid);
		Dashboard dashboard = dashboardService.getDashboardById(section.getDashboard().getId());
		for (User user : dashboard.getUsers()) {
			user.getCards().removeAll(section.getCards());
			user.getNotifications().add(this.notificationFactory.fillSectionDeletedTemplate(section, user));
			userService.updateUser(user);
		}
		dashboard.getSections().remove(section);
		section.setDashboard(null);
		dashboardService.updateDashboard(dashboard);
		sectionService.updateSection(section);
		sectionService.deleteSection(sectionid);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
}
