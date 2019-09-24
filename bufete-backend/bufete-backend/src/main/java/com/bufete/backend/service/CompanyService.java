package com.bufete.backend.service;

import com.bufete.backend.exception.ResourceNotFoundException;
import com.bufete.backend.model.Company;
import com.bufete.backend.model.StatusName;
import com.bufete.backend.payload.CompanyRequest;
import com.bufete.backend.repository.CompanyRepository;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  // private static final Logger logger =
  // LoggerFactory.getLogger(CompanyService.class);

  public Company createCompany(CompanyRequest companyRequest) {
    Company company = new Company(companyRequest.getName(), StatusName.ACTIVE);
    return companyRepository.save(company);
  }

  public Company getCompanyById(Long companyId) {
    return companyRepository.findById(companyId)
        .orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyId));
  }
}