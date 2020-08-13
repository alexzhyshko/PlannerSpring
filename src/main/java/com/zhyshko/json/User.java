package com.zhyshko.json;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {


	private UUID id;
	private String username;
	private String name;
	private String surname;
	private String email;
	private String password;
	private boolean enabled;
	private List<Dashboard> dashboards;
	private List<Card> cards;
	private List<Notification> notifications;

}
