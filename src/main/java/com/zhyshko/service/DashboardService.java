package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Dashboard;
import com.zhyshko.repository.CardRepository;
import com.zhyshko.repository.DashboardRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class DashboardService {

	private final DashboardRepository dashboardRepository;
	private ModelMapper mapper = new ModelMapper();
	
	
	@Transactional
	public com.zhyshko.dto.Dashboard getDashboardByUser(String username, UUID dashboardid) {
		com.zhyshko.dto.Dashboard dashboard = getDashboardById(dashboardid);
		if(dashboard==null) {
			return null;
		}
		boolean userHasAccess = false;
		for(com.zhyshko.dto.User user : dashboard.getUsers()) {
			if(user.getUsername().equalsIgnoreCase(username)) {
				userHasAccess = true;
				break;
			}
		}
		return userHasAccess?dashboard:null;
	}
	
	
	@Transactional
	public com.zhyshko.dto.Dashboard addDashboard(com.zhyshko.dto.Dashboard dashboard) {
		return mapper.map(dashboardRepository.save(mapper.map(dashboard, com.zhyshko.model.Dashboard.class)), com.zhyshko.dto.Dashboard.class);
	}
	
	
	@Transactional
	public List<com.zhyshko.dto.Dashboard> getAllDashboards(){
		List<com.zhyshko.dto.Dashboard> result = new ArrayList<>();
		for(Dashboard dashboard : dashboardRepository.findAll()) {
			result.add(mapper.map(dashboard, com.zhyshko.dto.Dashboard.class));
		}
		return result;
	}
	
	
	@Transactional
	public com.zhyshko.dto.Dashboard getDashboardById(UUID id){
		return mapper.map(dashboardRepository.findById(id).orElse(null), com.zhyshko.dto.Dashboard.class);
	}
	
	
	@Transactional
	public void deleteDashboard(UUID id){
		dashboardRepository.deleteById(id);
	}
	
	
	@Transactional
	public com.zhyshko.dto.Dashboard updateDashboard(com.zhyshko.dto.Dashboard dashboard){
		return 	mapper.map(dashboardRepository.save(mapper.map(dashboard, com.zhyshko.model.Dashboard.class)), com.zhyshko.dto.Dashboard.class);
	}
	
	
}
