package com.bufete.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bufete.backend.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "currencies")
public class Currency extends UserDateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @NotBlank
  @Size(max = 100)
  private String name;

  @NotBlank
  @Size(max = 10)
  @Column(name = "short_name")
  private String shortName;

  @Column(name = "exchange_value")
  private Double exchangeValue;

  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private StatusName status;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "company_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Company company;

  public Currency(String name, String shortName, Double exchangeValue, StatusName status, Company company) {
    this.name = name;
    this.shortName = shortName;
    this.exchangeValue = exchangeValue;
    this.status = status;
    this.company = company;
  }

  public Currency() {
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public Double getExchangeValue() {
    return exchangeValue;
  }

  public void setExchangeValue(Double exchangeValue) {
    this.exchangeValue = exchangeValue;
  }

  public StatusName getStatus() {
    return status;
  }

  public void setStatus(StatusName status) {
    this.status = status;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  private static final long serialVersionUID = 1L;
}