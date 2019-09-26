package com.bufete.backend.payload;

public class AssignRequest {

  private Long userId;
  private Long companyId;
  private Long[] rolesId;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  public Long[] getRolesId() {
    return rolesId;
  }

  public void setRolesId(Long[] rolesId) {
    this.rolesId = rolesId;
  }

}