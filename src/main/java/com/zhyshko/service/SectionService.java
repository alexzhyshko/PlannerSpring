package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Dashboard;
import com.zhyshko.model.Section;
import com.zhyshko.model.User;
import com.zhyshko.repository.SectionRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class SectionService {

	private final SectionRepository sectionRepository;
	
	
	@Transactional
	public Section addSection(Section section) {
		return sectionRepository.save(section);
	}
	
	
	
	@Transactional
	public Section getSectionByUser(String username, UUID dashboardid, UUID sectionid) {
		Section section = getSectionById(sectionid);
		if(section==null) {
			return null;
		}
		Dashboard dashboard = section.getDashboard();
		if(!dashboard.getId().equals(dashboardid)) {
			return null;
		}
		boolean owns = false;
		for(User user : dashboard.getUsers()) {
			if(user.getUsername().equals(username)) {
				owns = true;
				break;
			}
		}
		return owns?section:null;
	}
	
	
	@Transactional
	public List<Section> getAllSections(){
		List<Section> result = new ArrayList<>();
		for(Section section : sectionRepository.findAll()) {
			result.add(section);
		}
		return result;
	}
	
	
	@Transactional
	public Section getSectionById(UUID id){
		return sectionRepository.findById(id).orElse(null);
	}
	
	
	@Transactional
	public void deleteSection(UUID id){
		sectionRepository.deleteById(id);
	}
	
	
	@Transactional
	public Section updateSection(Section section){
		return sectionRepository.save(section);
	}
	
	
}
