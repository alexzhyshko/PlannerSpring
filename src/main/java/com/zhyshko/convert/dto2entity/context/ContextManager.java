package com.zhyshko.convert.dto2entity.context;

public class ContextManager {

	private ContextManager() {
	}

	private static CycleAvoidingMappingContextUserToEntity userContext;
	private static CycleAvoidingMappingContextDashboardToEntity dashboardContext;
	private static CycleAvoidingMappingContextSectionToEntity sectionContext;
	private static CycleAvoidingMappingContextCardToEntity cardContext;
	private static CycleAvoidingMappingContextNotificationToEntity notificationContext;

	public static CycleAvoidingMappingContextUserToEntity getUserContext() {
		return userContext == null ? (userContext = new CycleAvoidingMappingContextUserToEntity()) : userContext;
	}

	public static CycleAvoidingMappingContextDashboardToEntity getDashboardContext() {
		return dashboardContext == null ? (dashboardContext = new CycleAvoidingMappingContextDashboardToEntity())
				: dashboardContext;
	}

	public static CycleAvoidingMappingContextSectionToEntity getSectionContext() {
		return sectionContext == null ? (sectionContext = new CycleAvoidingMappingContextSectionToEntity())
				: sectionContext;
	}

	public static CycleAvoidingMappingContextCardToEntity getCardContext() {
		return cardContext == null ? (cardContext = new CycleAvoidingMappingContextCardToEntity()) : cardContext;
	}

	public static CycleAvoidingMappingContextNotificationToEntity getNotificationContext() {
		return notificationContext == null
				? (notificationContext = new CycleAvoidingMappingContextNotificationToEntity())
				: notificationContext;
	}

	public static void resetContext() {
		userContext = null;
		dashboardContext = null;
		sectionContext = null;
		cardContext = null;
		notificationContext = null;
	}

}
