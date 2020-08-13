package com.zhyshko.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
	

	private UUID id;
	private String title;
	private String text;
	@Builder.Default
	private boolean isSeen = false;
	private User owner;

}
