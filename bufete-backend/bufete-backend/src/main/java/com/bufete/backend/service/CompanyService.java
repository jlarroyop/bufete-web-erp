package com.bufete.backend.service;

import java.util.Collections;
import java.util.List;

import com.bufete.backend.exception.ResourceNotFoundException;
import com.bufete.backend.model.Company;
import com.bufete.backend.model.StatusName;
import com.bufete.backend.payload.CompanyRequest;
import com.bufete.backend.payload.CompanyResponse;
import com.bufete.backend.payload.PagedResponse;
import com.bufete.backend.repository.CompanyRepository;
import com.bufete.backend.security.UserPrincipal;
import com.bufete.backend.util.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  public PagedResponse<CompanyResponse> getAllCompanies(UserPrincipal currentUser, int page, int size) {
    Validators.validatePageNumberAndSize(page, size);

    Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
    Page<Company> companies = companyRepository.findByStatus(StatusName.ACTIVE, pageable);

    if (companies.getNumberOfElements() == 0) {
      return new PagedResponse<>(Collections.emptyList(), companies.getNumber(), companies.getSize(),
          companies.getTotalElements(), companies.getTotalPages(), companies.isLast());
    }

    List<CompanyResponse> companyResponses = companies.map(company -> {
      return new CompanyResponse(company.getId(), company.getName(), company.getCreatedAt());
    }).getContent();

    return new PagedResponse<>(companyResponses, companies.getNumber(), companies.getSize(),
        companies.getTotalElements(), companies.getTotalPages(), companies.isLast());
  }

  public Company createCompany(CompanyRequest companyRequest) {
    Company company = new Company(companyRequest.getName(), StatusName.ACTIVE);
    return companyRepository.save(company);
  }

  public Company getCompanyById(Long companyId) {
    return companyRepository.findById(companyId)
        .orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyId));
  }

  public void updateCompany(CompanyRequest companyRequest) {
    Company company = companyRepository.findById(companyRequest.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyRequest.getId()));
    company.setName(companyRequest.getName());
    company.setStatus(companyRequest.getStatus());
    companyRepository.save(company);
  }

  public void deleteCompany(Long companyId) {
    Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyId));
    company.setStatus(StatusName.DELETED);
    companyRepository.save(company);
  }
}