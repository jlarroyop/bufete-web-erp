package com.bufete.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.bufete.backend.model.audit.DateAudit;

@Entity
@Table(name = "app_options")
public class AppOption extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(length = 100)
  private String name;

  @NotBlank
  @Column(length = 150)
  private String path;

  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private StatusName status;

  @ManyToOne(cascade = { CascadeType.ALL })
  @JoinColumn(name = "parent_id")
  private AppOption parent_app_option;

  @OneToMany(mappedBy = "childAppOptions")
  private Set<AppOption> childAppOptions = new HashSet<AppOption>();

  @OneToMany(mappedBy = "appOption")
  private Set<RoleAppOption> roleAppOption;

  public AppOption(String name, String path, StatusName status) {
    this.name = name;
    this.path = path;
    this.status = status;
  }

  public AppOption() {
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

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public StatusName getStatus() {
    return status;
  }

  public void setStatus(StatusName status) {
    this.status = status;
  }

  public AppOption getParent_app_option() {
    return parent_app_option;
  }

  public void setParent_app_option(AppOption parent_app_option) {
    this.parent_app_option = parent_app_option;
  }

  public Set<AppOption> getChildAppOptions() {
    return childAppOptions;
  }

  public void setChildAppOptions(Set<AppOption> childAppOptions) {
    this.childAppOptions = childAppOptions;
  }

  public Set<RoleAppOption> getRoleOptions() {
    return roleAppOption;
  }

  public void setRoleOptions(Set<RoleAppOption> roleAppOption) {
    this.roleAppOption = roleAppOption;
  }

  private static final long serialVersionUID = 1L;
}