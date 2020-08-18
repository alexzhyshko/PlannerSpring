package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Section;
import com.zhyshko.repository.SectionRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class SectionService {

	private final SectionRepository sectionRepository;
	
	@Transactional
	public com.zhyshko.model.Section addSection(com.zhyshko.model.Section section) {
		return  sectionRepository.save(section);
	}
	
	
	
	@Transactional
	public com.zhyshko.model.Section getSectionByUser(String username, UUID dashboardid, UUID sectionid) {
		com.zhyshko.model.Section section = getSectionById(sectionid);
		if(section==null) {
			return null;
		}
		com.zhyshko.model.Dashboard dashboard = section.getDashboard();
		if(!dashboard.getId().equals(dashboardid)) {
			return null;
		}
		boolean owns = false;
		for(com.zhyshko.model.User user : dashboard.getUsers()) {
			if(user.getUsername().equals(username)) {
				owns = true;
				break;
			}
		}
		return owns?section:null;
	}
	
	
	@Transactional
	public List<com.zhyshko.model.Section> getAllSections(){
		List<com.zhyshko.model.Section> result = new ArrayList<>();
		for(Section section : sectionRepository.findAll()) {
			result.add(section);
		}
		return result;
	}
	
	
	@Transactional
	public com.zhyshko.model.Section getSectionById(UUID id){
		return sectionRepository.findById(id).orElse(null);
	}
	
	
	@Transactional
	public void deleteSection(UUID id){
		sectionRepository.deleteById(id);
	}
	
	
	@Transactional
	public com.zhyshko.model.Section updateSection(com.zhyshko.model.Section section){
		return addSection(section);
	}
	
	
}
