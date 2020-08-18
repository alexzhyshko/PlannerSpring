package com.zhyshko.convert.entity2dto;

import java.util.ArrayList;
import java.util.List;

import com.zhyshko.convert.entity2dto.context.ContextManager;
import com.zhyshko.convert.entity2dto.context.CycleAvoidingMappingContextSectionToDto;
import com.zhyshko.dto.Section.SectionBuilder;
import com.zhyshko.model.Section;

public class SectionEntityToDTO {

	public static com.zhyshko.dto.Section sectionToSection(Section section) {
		if (section == null) {
			return null;
		}

		CycleAvoidingMappingContextSectionToDto context = ContextManager.getSectionContext();

		com.zhyshko.dto.Section contextSection = context.getMappedInstance(section, com.zhyshko.dto.Section.class);
		
		if (contextSection == null) {
			SectionBuilder section1 = com.zhyshko.dto.Section.builder();
			section1.id(section.getId());
			section1.title(section.getTitle());
			com.zhyshko.dto.Section result = section1.build();
			context.storeMappedInstance(section, result);
			return result;
		}

		if(contextSection.getCards() == null) {
			contextSection.setCards(CardEntityToDTO.cardListToCardList(section.getCards()));
		}

		if(contextSection.getDashboard() == null) {
			contextSection.setDashboard(DashboardEntityToDTO.dashboardToDashboard(section.getDashboard()));
		}
		
		context.storeMappedInstance(section, contextSection);
		return contextSection;

		
	}

	public static List<com.zhyshko.dto.Section> sectionListToSectionList(List<Section> list) {
		if (list == null) {
			return null;
		}

		List<com.zhyshko.dto.Section> list1 = new ArrayList<>(list.size());
		for (Section section : list) {
			list1.add(sectionToSection(section));
		}

		return list1;
	}
}
