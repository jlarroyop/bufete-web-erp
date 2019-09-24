package com.bufete.backend.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyRequest {

  @NotBlank
  @Size(max = 150)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}