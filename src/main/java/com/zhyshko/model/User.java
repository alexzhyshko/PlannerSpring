package com.zhyshko.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	@Column(unique = true)
	private String username;
	private String name;
	private String surname;
	@Column(unique = true)
	private String email;
	private String password;
	private boolean enabled;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="users_dashboards",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="dashboard_id"))
	private List<Dashboard> dashboards;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="users_cards",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="card_id"))
	private List<Card> cards;
	
	
	@OneToMany(mappedBy = "owner")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Notification> notifications;

}
