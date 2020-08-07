package com.zhyshko.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "notifications")
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
	
	private UUID id;
	private String title;
	private String text;
	@Builder.Default
	private boolean isSeen = false;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	@Cascade(CascadeType.ALL)
	private User owner;

}
