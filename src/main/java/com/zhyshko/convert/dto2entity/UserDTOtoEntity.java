package com.zhyshko.convert.dto2entity;

import java.util.ArrayList;
import java.util.List;

import com.zhyshko.convert.dto2entity.context.ContextManager;
import com.zhyshko.convert.dto2entity.context.CycleAvoidingMappingContextUserToEntity;
import com.zhyshko.model.User;
import com.zhyshko.model.User.UserBuilder;

public class UserDTOtoEntity {

	public static User userDtoToUser(com.zhyshko.dto.User user) {
		if (user == null) {
			return null;
		}

		CycleAvoidingMappingContextUserToEntity context = ContextManager.getUserContext();

		if (context.getMappedInstance(user, com.zhyshko.model.User.class) != null) {
			return context.getMappedInstance(user, com.zhyshko.model.User.class);
		}
		
		UserBuilder user1 = User.builder();

		user1.id(user.getId());
		user1.username(user.getUsername());
		user1.name(user.getName());
		user1.surname(user.getSurname());
		user1.email(user.getEmail());
		user1.password(user.getPassword());
		user1.enabled(user.isEnabled());
		user1.dashboards(DashboardDTOtoEntity.dashboardListToDashboardList(user.getDashboards()));
		user1.cards(CardDTOtoEntity.cardListToCardList(user.getCards()));
		user1.notifications(NotificationDTOtoEntity.notificationListToNotificationList(user.getNotifications()));

		com.zhyshko.model.User result = user1.build();

		context.storeMappedInstance(user, result);

		return result;
	}

	public static List<User> userListToUserList(List<com.zhyshko.dto.User> list) {
		if (list == null) {
			return null;
		}

		List<User> list1 = new ArrayList<User>(list.size());
		for (com.zhyshko.dto.User user : list) {
			list1.add(userDtoToUser(user));
		}

		return list1;
	}

}
