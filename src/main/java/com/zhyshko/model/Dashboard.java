package com.zhyshko.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	private String title;
	@OneToMany(mappedBy = "dashboard")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	List<Section> sections;

	@ManyToMany(mappedBy = "dashboards", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	//@JsonManagedReference
	@JsonIgnore
	List<User> users;
//
//	@OneToOne
//	//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
//	//@JsonManagedReference
//	@JsonIgnore
//	User creator;
	
	@Column(columnDefinition = "BINARY(16)")
	UUID creatorId;

}