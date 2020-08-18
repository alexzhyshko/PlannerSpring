package com.zhyshko.factory;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {

	private String deleteDashboardTemplateText = "':dashboard' was deleted";
	private String addCardTemplateText = "':card' card was added to a dashboard";
	private String addSectionTemplateText = "':section' section was added to a dashboard";
	private String deleteCardTemplateText = "':card' card was added from a dashboard";
	private String deleteSectionCardTemplateText = "':section' section was deleted from a dashboard";
	private String addUserToCardTemplateText = ":username joined a ':card' card, which you are member of";
	private String leaveUserFromCardTemplateText = ":username left a ':card' card, which you are member of";
	private String cardDeadlineTomorrowTemplateText = "Tomorow is deadline of ':card' card";
	private String cardDeadlineIn2DaysTemplateText = "In two days is deadline of ':card' card";
	private String cardDeadlineInWeekTemplateText = "In a week is deadline of ':card' card";
	
	public com.zhyshko.model.Notification fillCardAddedTemplate(com.zhyshko.model.Card card, com.zhyshko.model.User user){
		return com.zhyshko.model.Notification.builder()
				.title(card.getSection().getDashboard().getTitle()+"#"+card.getSection().getTitle())
				.text(addCardTemplateText.replace(":card", card.getTitle()))
				.owner(user)
				.build();
	}
	
	public com.zhyshko.model.Notification fillSectionAddedTemplate(com.zhyshko.model.Section section, com.zhyshko.model.User user){
		return com.zhyshko.model.Notification.builder()
				.title(section.getDashboard().getTitle()+"#"+section.getTitle())
				.text(addSectionTemplateText.replace(":section", section.getTitle()))
				.owner(user)
				.created(LocalDateTime.now())
				.build();
	}
	
	public com.zhyshko.model.Notification fillCardDeletedTemplate(com.zhyshko.model.Card card, com.zhyshko.model.User user){
		return com.zhyshko.model.Notification.builder()
				.title(card.getSection().getDashboard().getTitle()+"#"+card.getSection().getTitle())
				.text(deleteCardTemplateText.replace(":card", card.getTitle()))
				.owner(user)
				.created(LocalDateTime.now())
				.build();
	}
	
	public com.zhyshko.model.Notification fillSectionDeletedTemplate(com.zhyshko.model.Section section, com.zhyshko.model.User user){
		return com.zhyshko.model.Notification.builder()
				.title(section.getDashboard().getTitle()+"#"+section.getTitle())
				.text(deleteSectionCardTemplateText.replace(":section", section.getTitle()))
				.owner(user)
				.created(LocalDateTime.now())
				.build();
	}
	
	public com.zhyshko.model.Notification fillUserJoinedCardTemplate(com.zhyshko.model.Card card, com.zhyshko.model.User user){
		return com.zhyshko.model.Notification.builder()
				.title(card.getSection().getDashboard().getTitle()+"#"+card.getSection().getTitle())
				.text(addUserToCardTemplateText.replace(":card", card.getTitle()).replace(":username", user.getUsername()))
				.owner(user)
				.created(LocalDateTime.now())
				.build();
	}
	
	public com.zhyshko.model.Notification fillUserLeftCardTemplate(com.zhyshko.model.Card card, com.zhyshko.model.User user){
		return com.zhyshko.model.Notification.builder()
				.title(card.getSection().getDashboard().getTitle()+"#"+card.getSection().getTitle())
				.text(leaveUserFromCardTemplateText.replace(":card", card.getTitle()).replace(":username", user.getUsername()))
				.owner(user)
				.created(LocalDateTime.now())
				.build();
	}
	

	public com.zhyshko.model.Notification fillCardTomorrowDeadlineTemplate(com.zhyshko.model.Card card, com.zhyshko.model.User user){
		return com.zhyshko.model.Notification.builder()
				.title(card.getSection().getDashboard().getTitle()+"#"+card.getSection().getTitle())
				.text(cardDeadlineTomorrowTemplateText.replace(":card", card.getTitle()))
				.owner(user)
				.created(LocalDateTime.now())
				.build();
	}
	
	public com.zhyshko.model.Notification fillCardIn2DaysDeadlineTemplate(com.zhyshko.model.Card card, com.zhyshko.model.User user){
		return com.zhyshko.model.Notification.builder()
				.title(card.getSection().getDashboard().getTitle()+"#"+card.getSection().getTitle())
				.text(cardDeadlineIn2DaysTemplateText.replace(":card", card.getTitle()))
				.owner(user)
				.created(LocalDateTime.now())
				.build();
	}
	
	public com.zhyshko.model.Notification fillCardInWeekDeadlineTemplate(com.zhyshko.model.Card card, com.zhyshko.model.User user){
		return com.zhyshko.model.Notification.builder()
				.title(card.getSection().getDashboard().getTitle()+"#"+card.getSection().getTitle())
				.text(cardDeadlineInWeekTemplateText.replace(":card", card.getTitle()))
				.owner(user)
				.created(LocalDateTime.now())
				.build();
	}
	
	public com.zhyshko.model.Notification fillDashboardDeletedTemplate(com.zhyshko.model.Dashboard dashboard, com.zhyshko.model.User user){
		return com.zhyshko.model.Notification.builder()
				.title(dashboard.getTitle())
				.text(deleteDashboardTemplateText.replace(":dashboard", dashboard.getTitle()))
				.owner(user)
				.created(LocalDateTime.now())
				.build();
	}
	
	
	
}
