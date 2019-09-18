package com.bufete.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.bufete.backend.model.audit.DateAudit;

@Entity
@Table(name = "roles")
public class Role extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(length = 60, unique = true)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private StatusName status;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent_id")
	private Role parent_role;

	@OneToMany(mappedBy = "childRoles")
	private Set<Role> childRoles = new HashSet<Role>();

	@OneToMany(mappedBy = "role")
	private Set<RoleAssign> registrations;

	@OneToMany(mappedBy = "roleOption")
	private Set<RoleAppOption> roleAppOption;

	public Role(String name, StatusName status) {
		super();
		this.name = name;
		this.status = status;
	}

	public Role() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StatusName getStatus() {
		return status;
	}

	public void setStatus(StatusName status) {
		this.status = status;
	}

	public Role getParent_Role() {
		return parent_role;
	}

	public void setParent_Role(Role parent_role) {
		this.parent_role = parent_role;
	}

	public Role getParent_role() {
		return parent_role;
	}

	public void setParent_role(Role parent_role) {
		this.parent_role = parent_role;
	}

	public Set<Role> getChildRoles() {
		return childRoles;
	}

	public void setChildRoles(Set<Role> childRoles) {
		this.childRoles = childRoles;
	}

	public Set<RoleAssign> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Set<RoleAssign> registrations) {
		this.registrations = registrations;
	}

	public Set<RoleAppOption> getRoleOptions() {
		return roleAppOption;
	}

	public void setRoleOptions(Set<RoleAppOption> roleAppOption) {
		this.roleAppOption = roleAppOption;
	}

	private static final long serialVersionUID = 1L;
}
