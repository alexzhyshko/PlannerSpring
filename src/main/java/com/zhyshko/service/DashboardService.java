package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Dashboard;
import com.zhyshko.repository.DashboardRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class DashboardService {

	private final DashboardRepository dashboardRepository;
	
	
	@Transactional
	public Dashboard addDashboard(Dashboard dashboard) {
		return dashboardRepository.save(dashboard);
	}
	
	
	@Transactional
	public List<Dashboard> getAllDashboards(){
		List<Dashboard> result = new ArrayList<>();
		for(Dashboard dashboard : dashboardRepository.findAll()) {
			result.add(dashboard);
		}
		return result;
	}
	
	
	@Transactional
	public Dashboard getDashboardById(UUID id){
		return dashboardRepository.findById(id).orElse(null);
	}
	
	
	@Transactional
	public void deleteDashboard(UUID id){
		dashboardRepository.deleteById(id);
	}
	
	
	@Transactional
	public Dashboard updateDashboard(Dashboard dashboard){
		return 	dashboardRepository.save(dashboard);
	}
	
	
}
