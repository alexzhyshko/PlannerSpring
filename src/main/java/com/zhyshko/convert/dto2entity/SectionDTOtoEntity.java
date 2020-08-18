package com.zhyshko.convert.dto2entity;

import java.util.ArrayList;
import java.util.List;

import com.zhyshko.convert.dto2entity.context.ContextManager;
import com.zhyshko.convert.dto2entity.context.CycleAvoidingMappingContextSectionToEntity;
import com.zhyshko.model.Section;
import com.zhyshko.model.Section.SectionBuilder;

public class SectionDTOtoEntity {
	public static Section sectionToSection(com.zhyshko.dto.Section section) {
		if (section == null) {
			return null;
		}

		CycleAvoidingMappingContextSectionToEntity context = ContextManager.getSectionContext();

		if (context.getMappedInstance(section, com.zhyshko.model.Section.class) != null) {
			return context.getMappedInstance(section, com.zhyshko.model.Section.class);
		}
		
		SectionBuilder section1 = Section.builder();

		section1.id(section.getId());
		section1.title(section.getTitle());
		section1.cards(CardDTOtoEntity.cardListToCardList(section.getCards()));
		section1.dashboard(DashboardDTOtoEntity.dashboardToDashboard(section.getDashboard()));

		com.zhyshko.model.Section result = section1.build();

		context.storeMappedInstance(section, result);

		return result;
	}

	public static List<Section> sectionListToSectionList(List<com.zhyshko.dto.Section> list) {
		if (list == null) {
			return null;
		}

		List<Section> list1 = new ArrayList<Section>(list.size());
		for (com.zhyshko.dto.Section section : list) {
			list1.add(sectionToSection(section));
		}

		return list1;
	}

}
