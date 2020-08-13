package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Section;
import com.zhyshko.repository.CardRepository;
import com.zhyshko.repository.SectionRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class SectionService {

	private final SectionRepository sectionRepository;
	private ModelMapper mapper = new ModelMapper();
	
	@Transactional
	public com.zhyshko.dto.Section addSection(com.zhyshko.dto.Section section) {
		return  mapper.map(sectionRepository.save(mapper.map(section, com.zhyshko.model.Section.class)), com.zhyshko.dto.Section.class);
	}
	
	
	
	@Transactional
	public com.zhyshko.dto.Section getSectionByUser(String username, UUID dashboardid, UUID sectionid) {
		com.zhyshko.dto.Section section = getSectionById(sectionid);
		if(section==null) {
			return null;
		}
		com.zhyshko.dto.Dashboard dashboard = section.getDashboard();
		if(!dashboard.getId().equals(dashboardid)) {
			return null;
		}
		boolean owns = false;
		for(com.zhyshko.dto.User user : dashboard.getUsers()) {
			if(user.getUsername().equals(username)) {
				owns = true;
				break;
			}
		}
		return owns?section:null;
	}
	
	
	@Transactional
	public List<com.zhyshko.dto.Section> getAllSections(){
		List<com.zhyshko.dto.Section> result = new ArrayList<>();
		for(Section section : sectionRepository.findAll()) {
			result.add(mapper.map(section, com.zhyshko.dto.Section.class));
		}
		return result;
	}
	
	
	@Transactional
	public com.zhyshko.dto.Section getSectionById(UUID id){
		return mapper.map(sectionRepository.findById(id).orElse(null), com.zhyshko.dto.Section.class);
	}
	
	
	@Transactional
	public void deleteSection(UUID id){
		sectionRepository.deleteById(id);
	}
	
	
	@Transactional
	public com.zhyshko.dto.Section updateSection(com.zhyshko.dto.Section section){
		return addSection(section);
	}
	
	
}
