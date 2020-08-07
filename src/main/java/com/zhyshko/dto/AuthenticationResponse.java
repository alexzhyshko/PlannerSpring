package com.zhyshko.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

	private String authToken;
	private String username;
	private Instant expiresAt;
	private String refreshToken;
	
}
