package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Dashboard;
import com.zhyshko.repository.DashboardRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class DashboardService {

	private final DashboardRepository dashboardRepository;
	
	@Transactional
	public com.zhyshko.model.Dashboard getDashboardByUser(String username, UUID dashboardid) {
		com.zhyshko.model.Dashboard dashboard = getDashboardById(dashboardid);
		if(dashboard==null) {
			return null;
		}
		boolean userHasAccess = false;
		for(com.zhyshko.model.User user : dashboard.getUsers()) {
			if(user.getUsername().equalsIgnoreCase(username)) {
				userHasAccess = true;
				break;
			}
		}
		return userHasAccess?dashboard:null;
	}
	
	
	@Transactional
	public com.zhyshko.model.Dashboard addDashboard(com.zhyshko.model.Dashboard dashboard) {
		return dashboardRepository.save(dashboard);
	}
	
	
	@Transactional
	public List<com.zhyshko.model.Dashboard> getAllDashboards(){
		List<com.zhyshko.model.Dashboard> result = new ArrayList<>();
		for(Dashboard dashboard : dashboardRepository.findAll()) {
			result.add(dashboard);
		}
		return result;
	}
	
	
	@Transactional
	public com.zhyshko.model.Dashboard getDashboardById(UUID id){
		return dashboardRepository.findById(id).orElse(null);
	}
	
	
	@Transactional
	public void deleteDashboard(UUID id){
		dashboardRepository.deleteById(id);
	}
	
	
	@Transactional
	public com.zhyshko.model.Dashboard updateDashboard(com.zhyshko.model.Dashboard dashboard){
		return addDashboard(dashboard);
	}
	
	
}
