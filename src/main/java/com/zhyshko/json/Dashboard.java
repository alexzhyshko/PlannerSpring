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
public class Dashboard {


	private UUID id;
	private String title;
	List<Section> sections;
	List<UserBack> users;
	UUID creatorId;

}
