package com.zhyshko.json;

import java.time.LocalDate;
import java.time.LocalTime;
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
public class Card {
	
	private UUID id;
	private String title;
	private String description;
	private String bottomTitle;
	private LocalDate date;
	private LocalTime time;
	private SectionBack section;
	private List<UserBack> workers;
	

}
