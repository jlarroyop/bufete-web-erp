package com.bufete.backend.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.bufete.backend.model.audit.DateAudit;

@Entity
@Table(name = "user_role_company")
public class RoleAssign extends DateAudit {

  @EmbeddedId
  private UserRoleCompanyKey id;

  @ManyToOne
  @MapsId("user_id")
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @MapsId("role_id")
  @JoinColumn(name = "role_id")
  private Role role;

  @ManyToOne
  @MapsId("company_id")
  @JoinColumn(name = "company_id")
  private Company company;

  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private StatusName status;

  public RoleAssign(User user, Role role, Company company, StatusName status) {
    this.user = user;
    this.role = role;
    this.company = company;
    this.status = status;
  }

  public RoleAssign() {
  }

  public UserRoleCompanyKey getId() {
    return id;
  }

  public void setId(UserRoleCompanyKey id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public StatusName getStatus() {
    return status;
  }

  public void setStatus(StatusName status) {
    this.status = status;
  }

  private static final long serialVersionUID = 1L;
}