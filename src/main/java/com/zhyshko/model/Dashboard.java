package com.zhyshko.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "dashboards")
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	private String title;
	@OneToMany(mappedBy = "dashboard")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	List<Section> sections;

	@ManyToMany(mappedBy = "dashboards")
	List<User> users;

}
