package com.zhyshko.json;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBack {


	private UUID id;
	private String username;
	private String name;
	private String surname;
	private String email;
	private String password;
	private boolean enabled;



}
