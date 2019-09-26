package com.bufete.backend.controller;

import java.net.URI;

import javax.validation.Valid;

import com.bufete.backend.model.Company;
import com.bufete.backend.payload.ApiResponse;
import com.bufete.backend.payload.CompanyRequest;
import com.bufete.backend.payload.CompanyResponse;
import com.bufete.backend.payload.PagedResponse;
import com.bufete.backend.security.CurrentUser;
import com.bufete.backend.security.UserPrincipal;
import com.bufete.backend.service.CompanyService;
import com.bufete.backend.util.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @GetMapping
  public PagedResponse<CompanyResponse> getCompanies(@CurrentUser UserPrincipal currentUser,
      @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    return companyService.getAllCompanies(currentUser, page, size);
  }

  @PostMapping
  public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyRequest companyRequest) {
    Company company = companyService.createCompany(companyRequest);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{companyId}").buildAndExpand(company.getId())
        .toUri();

    return ResponseEntity.created(location).body(new ApiResponse(true, "Company Created Successfully"));
  }

  @GetMapping("/{companyId}")
  public Company getCompanyById(@PathVariable Long companyId) {
    return companyService.getCompanyById(companyId);
  }

  @PutMapping
  public ResponseEntity<?> updateCompany(@Valid @RequestBody CompanyRequest companyRequest) {
    companyService.updateCompany(companyRequest);
    return ResponseEntity.ok(new ApiResponse(true, "Company Updated Successfully"));
  }

  @DeleteMapping("/{companyId}")
  public ResponseEntity<?> deleteCompany(@PathVariable Long companyId) {
    companyService.deleteCompany(companyId);
    return ResponseEntity.ok(new ApiResponse(true, "Company Delleted Successfully"));
  }
}