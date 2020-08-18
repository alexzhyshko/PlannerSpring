package com.zhyshko.convert.entity2dto;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Component;

import com.zhyshko.convert.entity2dto.context.ContextManager;
import com.zhyshko.convert.entity2dto.context.CycleAvoidingMappingContextUserToDto;
import com.zhyshko.dto.User.UserBuilder;
import com.zhyshko.model.User;

@Generated(value = "org.mapstruct.ap.MappingProcessor", comments = "version: 1.4.0.Beta3, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)")
@Component
public class UserEntityToDTO {

	public static com.zhyshko.dto.User userToUserDto(User user) {

		if (user == null) {
			return null;
		}

		CycleAvoidingMappingContextUserToDto context = ContextManager.getUserContext();

		com.zhyshko.dto.User contextUser = context.getMappedInstance(user, com.zhyshko.dto.User.class);
		
		if(contextUser == null) {
			UserBuilder user1 = com.zhyshko.dto.User.builder();
			user1.id(user.getId());
			user1.username(user.getUsername());
			user1.name(user.getName());
			user1.surname(user.getSurname());
			user1.email(user.getEmail());
			user1.password(user.getPassword());
			user1.enabled(user.isEnabled());
			com.zhyshko.dto.User result = user1.build();
			context.storeMappedInstance(user, result);
			return result;
		}
		
		if(contextUser.getDashboards()==null) {
			if(ContextManager.getDashboardContext().getInstanceCount()>0) {
				contextUser.setDashboards(ContextManager.getDashboardContext().getAllKnownInstances());
			}else {
				contextUser.setDashboards(DashboardEntityToDTO.dashboardListToDashboardList(user.getDashboards()));
			}
		}

		if(contextUser.getCards()==null) {
			if(ContextManager.getCardContext().getInstanceCount()>0) {
				contextUser.setCards(ContextManager.getCardContext().getAllKnownInstances());
			}else {
				contextUser.setCards(CardEntityToDTO.cardListToCardList(user.getCards()));
			}
		}
		
		if(contextUser.getNotifications()==null) {
			if(ContextManager.getNotificationContext().getInstanceCount()>0) {
				contextUser.setNotifications(ContextManager.getNotificationContext().getAllKnownInstances());
			}else {
				contextUser.setNotifications(NotificationEntityToDTO.notificationListToNotificationList(user.getNotifications()));
			}
		}
		

		context.storeMappedInstance(user, contextUser);
		return contextUser;
	}

	public static List<com.zhyshko.dto.User> userListToUserList(List<User> list) {
		if (list == null) {
			return null;
		}

		List<com.zhyshko.dto.User> list1 = new ArrayList<com.zhyshko.dto.User>(list.size());
		for (User user : list) {
			list1.add(userToUserDto(user));
		}

		return list1;
	}

}
