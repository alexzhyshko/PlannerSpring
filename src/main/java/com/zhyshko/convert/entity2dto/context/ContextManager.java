package com.zhyshko.convert.entity2dto.context;

public class ContextManager {

	private ContextManager() {}
	
	private static CycleAvoidingMappingContextUserToDto userContext;
	private static CycleAvoidingMappingContextDashboardToDto dashboardContext;
	private static CycleAvoidingMappingContextSectionToDto sectionContext;
	private static CycleAvoidingMappingContextCardToDto cardContext;
	private static CycleAvoidingMappingContextNotificationToDto notificationContext;
	
	public static CycleAvoidingMappingContextUserToDto getUserContext() {
		return userContext==null?(userContext = new CycleAvoidingMappingContextUserToDto()):userContext;
	}
	
	public static CycleAvoidingMappingContextDashboardToDto getDashboardContext() {
		return dashboardContext==null?(dashboardContext = new CycleAvoidingMappingContextDashboardToDto()):dashboardContext;
	}
	
	public static CycleAvoidingMappingContextSectionToDto getSectionContext() {
		return sectionContext==null?(sectionContext = new CycleAvoidingMappingContextSectionToDto()):sectionContext;
	}
	
	public static CycleAvoidingMappingContextCardToDto getCardContext() {
		return cardContext==null?(cardContext = new CycleAvoidingMappingContextCardToDto()):cardContext;
	}
	
	public static CycleAvoidingMappingContextNotificationToDto getNotificationContext() {
		return notificationContext==null?(notificationContext = new CycleAvoidingMappingContextNotificationToDto()):notificationContext;
	}
	
	public static void resetContext() {
		userContext = null;
		dashboardContext = null;
		sectionContext = null;
		cardContext = null;
		notificationContext = null;
	}
	
	
}
