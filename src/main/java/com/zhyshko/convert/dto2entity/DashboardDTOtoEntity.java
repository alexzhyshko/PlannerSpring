package com.zhyshko.convert.dto2entity;

import java.util.ArrayList;
import java.util.List;

import com.zhyshko.convert.dto2entity.context.ContextManager;
import com.zhyshko.convert.dto2entity.context.CycleAvoidingMappingContextDashboardToEntity;
import com.zhyshko.model.Dashboard;
import com.zhyshko.model.Dashboard.DashboardBuilder;

public class DashboardDTOtoEntity {
	public static Dashboard dashboardToDashboard(com.zhyshko.dto.Dashboard dashboard) {
		if (dashboard == null) {
			return null;
		}

		CycleAvoidingMappingContextDashboardToEntity context = ContextManager.getDashboardContext();

		if (context.getMappedInstance(dashboard, com.zhyshko.model.Dashboard.class) != null) {
			return context.getMappedInstance(dashboard, com.zhyshko.model.Dashboard.class);
		}
		
		DashboardBuilder dashboard1 = Dashboard.builder();

		dashboard1.id(dashboard.getId());
		dashboard1.title(dashboard.getTitle());
		dashboard1.sections(SectionDTOtoEntity.sectionListToSectionList(dashboard.getSections()));
		dashboard1.users(UserDTOtoEntity.userListToUserList(dashboard.getUsers()));
		dashboard1.creatorId(dashboard.getCreatorId());

		com.zhyshko.model.Dashboard result = dashboard1.build();

		context.storeMappedInstance(dashboard, result);

		return result;
	}

	public static List<Dashboard> dashboardListToDashboardList(List<com.zhyshko.dto.Dashboard> list) {
		if (list == null) {
			return null;
		}

		List<Dashboard> list1 = new ArrayList<Dashboard>(list.size());
		for (com.zhyshko.dto.Dashboard dashboard : list) {
			list1.add(dashboardToDashboard(dashboard));
		}

		return list1;
	}

}