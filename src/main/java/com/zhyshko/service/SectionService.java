package com.zhyshko.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zhyshko.exception.UserNotFoundException;
import com.zhyshko.model.Section;
import com.zhyshko.repository.SectionRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class SectionService {

	private final SectionRepository sectionRepository;
	
	public Section addSection(Section section) {
		return sectionRepository.save(section);
	}
	
	public List<Section> getAllSections(){
		List<Section> result = new ArrayList<>();
		for(Section section : sectionRepository.findAll()) {
			result.add(section);
		}
		return result;
	}
	
	public Section getSectionById(UUID id){
		return sectionRepository.findById(id).orElse(null);
	}
	
	public void deleteSection(UUID id){
		sectionRepository.deleteById(id);
	}
	
	public Section updateSection(Section section){
		return sectionRepository.save(section);
	}
	
	
}
