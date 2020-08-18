package com.zhyshko.convert.entity2dto;

import java.util.ArrayList;
import java.util.List;

import com.zhyshko.convert.entity2dto.context.ContextManager;
import com.zhyshko.convert.entity2dto.context.CycleAvoidingMappingContextDashboardToDto;
import com.zhyshko.dto.Dashboard.DashboardBuilder;
import com.zhyshko.model.Dashboard;

public class DashboardEntityToDTO {

	public static com.zhyshko.dto.Dashboard dashboardToDashboard(Dashboard dashboard) {
		if (dashboard == null) {
			return null;
		}

		CycleAvoidingMappingContextDashboardToDto context = ContextManager.getDashboardContext();

		com.zhyshko.dto.Dashboard contextDashboard = context.getMappedInstance(dashboard, com.zhyshko.dto.Dashboard.class);

		DashboardBuilder dashboard1 = com.zhyshko.dto.Dashboard.builder();
		
		if (contextDashboard == null) {
			dashboard1.id(dashboard.getId());
			dashboard1.title(dashboard.getTitle());
			dashboard1.creatorId(dashboard.getCreatorId());
			com.zhyshko.dto.Dashboard result = dashboard1.build();
			context.storeMappedInstance(dashboard, result);
			return result;
		}
		
//		if(contextDashboard.getSections()==null) {
//			contextDashboard.setSections(SectionEntityToDTO.sectionListToSectionList(dashboard.getSections()));
//		}
//		
//		if(contextDashboard.getUsers()==null) {
//			contextDashboard.setUsers(UserEntityToDTO.userListToUserList(dashboard.getUsers()));
//		}
//		
		if(contextDashboard.getSections()==null) {
			if(ContextManager.getSectionContext().getInstanceCount()>0) {
				contextDashboard.setSections(ContextManager.getSectionContext().getAllKnownInstances());
			}else {
				contextDashboard.setSections(SectionEntityToDTO.sectionListToSectionList(dashboard.getSections()));
			}
		}

		if(contextDashboard.getUsers()==null) {
			if(ContextManager.getCardContext().getInstanceCount()>0) {
				contextDashboard.setUsers(ContextManager.getUserContext().getAllKnownInstances());
			}else {
				contextDashboard.setUsers(UserEntityToDTO.userListToUserList(dashboard.getUsers()));
			}
		}
		
		if(contextDashboard.getNotifications()==null) {
			if(ContextManager.getNotificationContext().getInstanceCount()>0) {
				contextDashboard.setNotifications(ContextManager.getNotificationContext().getAllKnownInstances());
			}else {
				contextDashboard.setNotifications(NotificationEntityToDTO.notificationListToNotificationList(user.getNotifications()));
			}
		}
		
		context.storeMappedInstance(dashboard, contextDashboard);
		return contextDashboard;
		
	}

	public static List<com.zhyshko.dto.Dashboard> dashboardListToDashboardList(List<Dashboard> list) {
		if (list == null) {
			return null;
		}
		List<com.zhyshko.dto.Dashboard> list1 = new ArrayList<>(list.size());
		for (Dashboard dashboard : list) {
			list1.add(dashboardToDashboard(dashboard));
		}
		return list1;
	}

}
