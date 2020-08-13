package com.zhyshko.dto;

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
public class Section {

	private UUID id;
	private String title;
	List<Card> cards;
	private Dashboard dashboard;

}
