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

import com.zhyshko.model.Dashboard;
import com.zhyshko.model.Section;
import com.zhyshko.service.DashboardService;
import com.zhyshko.service.SectionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	private final DashboardService dashboardService;
	private final SectionService sectionService;

	@PostMapping("/create")
	public ResponseEntity<String> addUser(@RequestBody Dashboard dashboard) {
		dashboardService.addDashboard(dashboard);
		return new ResponseEntity<>("Added", HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Dashboard> getAllDashboards() {
		return dashboardService.getAllDashboards();
	}
	
	@PostMapping("/createSection/{dashboardid}")
	public ResponseEntity<String> addSection(@PathVariable("dashboardid") UUID dashboardid, @RequestBody Section section) {
		Dashboard dashboard = dashboardService.getDashboardById(dashboardid);
		dashboard.getSections().add(section);
		section.setDashboard(dashboard);
		dashboardService.updateDashboard(dashboard);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/removeSection")
	public ResponseEntity<String> removeSection(@RequestBody Map<String, String> json) {
		UUID dashboardid = UUID.fromString(json.get("dashboardid"));
		UUID sectionid =  UUID.fromString(json.get("sectionid"));
		Dashboard dashboard = dashboardService.getDashboardById(dashboardid);
		Section section = sectionService.getSectionById(sectionid);
		section.setDashboard(null);
		dashboard.getSections().remove(section);
		dashboardService.updateDashboard(dashboard);
		return new ResponseEntity<>("Done", HttpStatus.CREATED);
	}
}
